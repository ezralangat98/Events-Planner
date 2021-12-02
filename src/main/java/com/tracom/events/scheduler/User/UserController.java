package com.tracom.events.scheduler.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage() {

        return "index";
    }

    @GetMapping("/add_user")
    public String showUserAddForm(Model model) {
        model.addAttribute("user", new User());
        return "User/add_user";
    }

    @PostMapping("/create_user")
    public String userCreate(User user) {

        userRepository.save(user);
        return "index";
    }

    @GetMapping("/list_user")
    public String viewUsersList(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "User/list_users";
    }

    @RequestMapping("/delete_user/{user_id}")
    public String deleteUser(@PathVariable(name = "user_id") Long user_id) {
        userRepository.deleteById(user_id);
        return "User/list_users";
    }

    @RequestMapping("/edit_user/{user_id}")
    public ModelAndView ShowEditUser(@PathVariable(name = "user_id") Long user_id) {
        ModelAndView umv = new ModelAndView("User/edit_user");
        User user = userRepository.getById(user_id);
        umv.addObject("editUser", user);
        return umv;

    }
}
