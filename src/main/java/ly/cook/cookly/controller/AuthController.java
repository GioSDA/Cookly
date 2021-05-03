package ly.cook.cookly.controller;

import ly.cook.cookly.model.Comment;
import ly.cook.cookly.model.Recipe;
import ly.cook.cookly.model.User;
import ly.cook.cookly.repository.RecipeRepository;
import ly.cook.cookly.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    RecipeRepository recipeRepository;

    public ModelAndView login() {
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
        mav.addObject("results", recipeRepository.findAll());
        return mav;
    }

}
