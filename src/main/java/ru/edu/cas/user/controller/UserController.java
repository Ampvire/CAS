package ru.edu.cas.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.service.ClientService;

/**
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private ClientService service;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping("/info")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/user_panel.jsp");
        modelAndView.addObject("list", service.getAllClients(8));
        modelAndView.addObject("segments",service.getListSegments());
        return modelAndView;
    }

}
