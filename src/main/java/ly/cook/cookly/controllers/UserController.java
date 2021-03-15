package ly.cook.cookly.controllers;

import ly.cook.cookly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public String getAllIssues(Model model) {
        model.addAttribute("users", repository.findAll());
        return "list-users";
    }
}
