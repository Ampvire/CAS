package ru.edu.cas.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.service.ClientService;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/user/client")
public class ClientController {
    private ClientService service;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping("/getAllClients")
    public ModelAndView getAllUserClients() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClients(2));
        modelAndView.setViewName("/client/all_clients.jsp");
        return modelAndView;
    }

    @GetMapping("/getNewClients")
    public ModelAndView getNew() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClientsWithoutUser(2));
        modelAndView.setViewName("/client/new_clients.jsp");
        return modelAndView;
    }

    @GetMapping("/newClient")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("segment", service.getListSegments());
        modelAndView.addObject("type", service.getListTypes());
        return new ModelAndView("/client/create_client.jsp");
    }

    @PostMapping("/update")
    public ModelAndView newUser(@RequestParam("name") String name,
                                @RequestParam("type") String type,
                                @RequestParam("inn") String inn,
                                @RequestParam("ogrn") String ogrn,
                                @RequestParam("segment") String segment) {
        SecurityProperties.User user=  new SecurityProperties.User();
        user.getName();
        service.createClient(name, type, inn, ogrn, segment, "2");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Client");
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

    @GetMapping("/getReport")
    public ModelAndView getReport() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", LocalDateTime.now());
        modelAndView.setViewName("/client/report.jsp");
        return modelAndView;
    }
}
