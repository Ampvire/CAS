package ru.edu.cas.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public ModelAndView getStartPage() {
        return new ModelAndView("/appPage/index.jsp");
    }

    @GetMapping(value = "/login")
    public ModelAndView geFormAuthorization() {
        return new ModelAndView("/appPage/login.jsp");
    }
}


