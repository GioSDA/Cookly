package ly.cook.cookly.controller;

import com.mongodb.client.MongoClients;
import ly.cook.cookly.model.Comment;
import ly.cook.cookly.model.Image;
import ly.cook.cookly.model.Recipe;
import ly.cook.cookly.model.User;
import ly.cook.cookly.repository.CommentRepository;
import ly.cook.cookly.repository.ImageRepository;
import ly.cook.cookly.repository.RecipeRepository;
import ly.cook.cookly.repository.UserRepository;
import ly.cook.cookly.service.CommentService;
import ly.cook.cookly.service.CustomUserDetailsService;
import ly.cook.cookly.service.ImageService;
import ly.cook.cookly.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

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

        Recipe r = recipeService.loadRecipeById(Integer.parseInt(recipeid));

        if (r != null) {
            mav.setViewName("recipe");
            mav.addObject("recipe", recipeService.loadRecipeById(Integer.parseInt(recipeid)));
        } else {
            mav.setStatus(HttpStatus.NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = "recipe/comment", method = RequestMethod.POST)
    public ModelAndView addComment(Comment comment, Recipe r, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        //Check if user is authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.isAuthenticated()) {
            bindingResult.rejectValue("user", "error.user", "User is not logged in.");
            return mav;
        }

       commentService.saveComment(comment);

        r.getComments().add(comment);
        recipeService.saveRecipe(r);

        return mav;
    }

}