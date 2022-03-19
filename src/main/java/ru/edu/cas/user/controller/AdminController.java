package ru.edu.cas.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.user.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/newUser")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/create_user.jsp");
        modelAndView.addObject("roles", service.getAllRole());
        modelAndView.addObject("categories", service.getAllCategory());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView newUser(@RequestParam("login") String login,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("secondName") String secondName,
                                @RequestParam("password") String password,
                                @RequestParam("category") String categoryName,
                                @RequestParam("role") String roleName) {
        service.createOrUpdateUser(login, firstName, secondName, password, categoryName, roleName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "User created!");
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/delete_user.jsp");
        return modelAndView;
    }

    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("login") String login) {
        service.deleteUser(login);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "User deleted!");
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

    @GetMapping("/allUsers")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllUsers());
        modelAndView.setViewName("/admin/all_users.jsp");
        return modelAndView;
    }

}