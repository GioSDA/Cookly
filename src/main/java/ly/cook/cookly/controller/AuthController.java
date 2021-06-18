package ly.cook.cookly.controller;

import com.mongodb.Mongo;
import com.mongodb.client.MongoClients;
import ly.cook.cookly.model.*;
import ly.cook.cookly.service.CommentService;
import ly.cook.cookly.service.CustomUserDetailsService;
import ly.cook.cookly.service.ImageService;
import ly.cook.cookly.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    MongoTemplate template = new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "cooklydb");

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(required = false) String redirect, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (redirect != null) session.setAttribute("url_prior_login", redirect);
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject(user);
        mav.setViewName("signup");
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        //Check if user exists
        if (userDetailsService.findUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a registered user with that email.");

            mav.setViewName("signup");
            return mav;
        }

        //If user does not exist
        userDetailsService.saveUser(user);

        mav.addObject("successMessage", "User has been registered successfully.");
        mav.addObject("user", new User());
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailsService.findUserByEmail(auth.getName());

        mav.addObject("currentUser", user);
        mav.addObject("name", "Welcome " + user.getName());
        mav.addObject("adminMessage", "Context Available Only for Users with Admin Role");
        mav.setViewName("dashboard");
        return mav;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value = {"/search/results"}, method = RequestMethod.GET)
    public ModelAndView searchresults(@RequestParam("q") String query) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("searchresults");
        mav.addObject("query", query);

        //Search Function
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(query);
        Query queryC = TextQuery.queryText(criteria).sortByScore();


        mav.addObject("results", template.find(queryC, Recipe.class));

        return mav;
    }

    @RequestMapping(value = {"/recipe/{id}"}, method = RequestMethod.GET)
    public ModelAndView recipe(@PathVariable("id") String recipeid) {
        ModelAndView mav = new ModelAndView();

//        Recipe re = new Recipe(0, "Test Chocolate Cake", "The testiest chocolate cake around", new ArrayList<Image>(imageRepository.findAll()), 1, 1, new ArrayList<String>(Arrays.asList("1 pound of Test")), steps, "Famous Cookbook", "This is a test", 100, new ArrayList<Comment>(Arrays.asList(commentRepository.findById(0).get())));

        Recipe r = recipeService.loadRecipeById(recipeid);

        if (r != null) {
            mav.setViewName("recipe");
            mav.addObject("recipe", recipeService.loadRecipeById(recipeid));
            mav.addObject("CommentDetails", new CommentDetails());
        } else {
            mav.setStatus(HttpStatus.NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = {"/recipe/{id}/comment"}, method = RequestMethod.GET)
    public ModelAndView recipeComment(@PathVariable("id") String recipeid) {
        ModelAndView mav = new ModelAndView();

//        Recipe re = new Recipe(0, "Test Chocolate Cake", "The testiest chocolate cake around", new ArrayList<Image>(imageRepository.findAll()), 1, 1, new ArrayList<String>(Arrays.asList("1 pound of Test")), steps, "Famous Cookbook", "This is a test", 100, new ArrayList<Comment>(Arrays.asList(commentRepository.findById(0).get())));

        Recipe r = recipeService.loadRecipeById(recipeid);

        if (r != null) {
            mav.setViewName("recipe");
            mav.addObject("recipe", recipeService.loadRecipeById(recipeid));
            mav.addObject("CommentDetails", new CommentDetails());
        } else {
            mav.setStatus(HttpStatus.NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = "/recipe/{id}/comment", method = RequestMethod.POST)
    public ModelAndView addComment(CommentDetails comment, @PathVariable("id") String recipeid, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        //Check if user is authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.isAuthenticated() || userDetailsService.findUserByEmail(auth.getName()) == null) {
            bindingResult.rejectValue("user", "error.user", "User is not logged in.");
            return mav;
        }

        Recipe r = recipeService.loadRecipeById(recipeid);

        if (r != null) {
            LocalDate myBirthday = LocalDate.of(2006, 8, 22);

            Comment c = new Comment(userDetailsService.findUserByEmail(auth.getName()), comment.getText(), comment.getRating(), myBirthday, 0);
            commentService.saveComment(c);

            r.getComments().add(commentService.loadAndUpdateUndatedComment(myBirthday));
            r.calcAverageRankings();
            recipeService.saveRecipe(r);

            mav.setViewName("recipe");
            mav.addObject("recipe", r);
            mav.addObject("CommentDetails", new CommentDetails());
        } else {
            mav.setStatus(HttpStatus.NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = "/recipe/create", method = RequestMethod.GET)
    public ModelAndView addRecipe(BindingResult bindingResult) {
        ModelAndView  mav = new ModelAndView();

        //Check if user is authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.isAuthenticated() || userDetailsService.findUserByEmail(auth.getName()) == null) {
            bindingResult.rejectValue("user", "error.user", "User is not logged in.");
            return mav;
        }

        mav.setViewName("addrecipe");

        return mav;
    }

}