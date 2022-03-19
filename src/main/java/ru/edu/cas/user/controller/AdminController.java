package ru.edu.cas.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/info.jsp");
        return modelAndView;
    }

    @GetMapping("/newUser")
    public ModelAndView newUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/create_user.jsp");
        modelAndView.addObject("roles", service.getAllRole());
        modelAndView.addObject("categories", service.getAllCategory());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@RequestParam("login") String login,
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

    @GetMapping("/updateUser/{login}")
    public ModelAndView updateUser(@PathVariable("login") String login) {
        User user = service.getUser(login);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/update_user.jsp");
        modelAndView.addObject("name", user.getFirstName());
        modelAndView.addObject("secondName", user.getLastName());
        modelAndView.addObject("password", user.getPassword());
        modelAndView.addObject("login", user.getLogin());
        modelAndView.addObject("category", user.getCategoryId().getCategory());
        modelAndView.addObject("role", user.getRoleId().getRole());
        return modelAndView;
    }

    @PostMapping("/updateUser/success")
    public ModelAndView updateUpdateSuccess(@RequestParam("login") String login,
                                            @RequestParam("firstName") String firstName,
                                            @RequestParam("secondName") String secondName,
                                            @RequestParam("password") String password,
                                            @RequestParam("category") String categoryName,
                                            @RequestParam("role") String roleName) {
        service.createOrUpdateUser(login, firstName, secondName, password, categoryName, roleName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "User update!");
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUserByLogin(@RequestParam("login") String login) {
        service.deleteUser(login);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "User deleted!");
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

    @PostMapping("/deleteUser/{login}")
    public ModelAndView deleteUser(@PathVariable("login") String login) {
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