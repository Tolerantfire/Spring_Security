package crud.controllers;

import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String UserPage(ModelMap model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "user/{id}")
    public String ShowUser(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("currentUsers", userService.queryForUser());
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationPage(@ModelAttribute("userForm") User userForm) {
        userService.addUser(userForm);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping()
    public String welcome(Model model) {
       return "hello";
    }

    @GetMapping(value = "/failure")
    public String failure(Model model) {
        return "failure";
    }

  }
