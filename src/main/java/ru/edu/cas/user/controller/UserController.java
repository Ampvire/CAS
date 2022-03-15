package ru.edu.cas.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/info")
    public ModelAndView info() {
        return new ModelAndView("/user/user_panel.jsp");
    }

}
