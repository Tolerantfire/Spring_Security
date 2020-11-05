package crud.controllers;

import crud.dao.UserDAO;
import crud.dao.UserDAOinterface;
import crud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestController2 {

    private final UserDAOinterface userDAO;

    public TestController2(UserDAOinterface userDAO) {
        this.userDAO = userDAO;
    }


    @GetMapping("/user")
    public String newUser(Model model) {
        model.addAttribute("currentUsers", userDAO.queryForUser());
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute("User") User user) {
        userDAO.saveUser(user.getName(), user.getAge());
        return "redirect:/user";
    }

    @GetMapping("/remove")
    public String remove(Model model) {
        model.addAttribute("user", new User());
        return "remove";
    }

    @DeleteMapping("/remove")
    public String removeUser(@ModelAttribute("User") User user) {
        userDAO.removeUser(user.getId());
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("user", new User());
        return "/edit";
    }

    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("User") User user) {
        userDAO.editUser(user.getId(), user.getName(), user.getAge());
        return "redirect:/user";
    }
}
