package ru.edu.cas.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.dao.ClientReport;
import ru.edu.cas.client.service.ClientService;

import java.time.LocalDateTime;
import java.util.List;

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
    public ModelAndView getNew(@RequestParam("segment") String segment) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClientsWithoutUser(segment));
        modelAndView.setViewName("/client/new_clients.jsp");
        return modelAndView;
    }

    @GetMapping("/newClient")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/create_client.jsp");
        modelAndView.addObject("segments", service.getListSegments());
        modelAndView.addObject("types", service.getListTypes());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView newUser(@RequestParam("name") String name,
                                @RequestParam("type") String type,
                                @RequestParam("inn") String inn,
                                @RequestParam("ogrn") String ogrn,
                                @RequestParam("segment") String segment) {
        SecurityProperties.User user = new SecurityProperties.User();
        user.getName();
        service.createClient(name, type, inn, ogrn, segment);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Client created!");
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

    @GetMapping("/getReport/{inn}")
    public ModelAndView getReport(@PathVariable("inn") String inn) {
        ModelAndView modelAndView = new ModelAndView();

        List<ClientFinance> finances = service.getAllFinanceByClientInn(inn);
        List<ClientReport> reports = service.getAllReportByClientInn(inn);
        modelAndView.addObject("finances", finances);
        modelAndView.addObject("report", reports);
        modelAndView.addObject("date", LocalDateTime.now());
        modelAndView.setViewName("/client/report.jsp");
        return modelAndView;
    }
}
