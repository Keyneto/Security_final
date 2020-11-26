package application.controllers;


import application.models.MyUser;
import application.models.Role;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("all_users")
    public String printUsers(ModelMap model) {
        List<MyUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser() {
        return "new_user";
    }

    @PostMapping("/create_new_user")
    public String createNewUser(
            @RequestParam("username") String username, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("role") String role) {
        MyUser userTwo = new MyUser(username, email, password, new Role(role));
        userService.save(userTwo);
        return "redirect:/admin/all_users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/all_users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") MyUser user, @PathVariable("id") Long id) {
        userService.udateUser(user, id);
        return "redirect:/admin/all_users";
    }
}