package ru.edu.cas.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.service.ClientService;


@Controller
@RequestMapping
public class mainController {
    private ClientService service;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/start_page.jsp");
        return modelAndView;
    }

}

