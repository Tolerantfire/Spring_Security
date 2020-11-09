package crud.controllers;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDAO;

    public UserController(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    //Надеюсь, правильно понял комментарии.
    // выводим список юзеров на url /user:
    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("currentUsers", userDAO.queryForUser());
        model.addAttribute("user", new User());
        return "user/showList";
    }

    //добавляем юзера на url /user/add:
    @GetMapping("/add")
    public String newUser(Model model) {
        model.addAttribute("currentUsers", userDAO.queryForUser());
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("User") User user) {
        userDAO.saveUser(user.getName(), user.getAge());
        return "redirect:/user";
    }

    //удаляем юзера по ID на url /user/remove:
    @GetMapping("/remove")
    public String remove(Model model) {
        model.addAttribute("user", new User());
        return "user/remove";
    }

    @DeleteMapping("/remove")
    public String removeUser(@ModelAttribute("User") User user) {
        userDAO.removeUser(user.getId());
        return "redirect:/user";
    }

    //редактируем юзера по ID на url /user/edit:
    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("User") User user) {
        userDAO.editUser(user.getId(), user.getName(), user.getAge());
        return "redirect:/user";
    }
}
