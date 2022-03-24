package ru.edu.cas.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.service.ClientService;
import ru.edu.cas.user.service.CustomDetailUserService;
import ru.edu.cas.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/user/client")
public class ClientController {
    private ClientService service;
    private CustomDetailUserService detailUserService;
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public CustomDetailUserService getDetailUserService() {
        return detailUserService;
    }

    public void setDetailUserService(CustomDetailUserService detailUserService) {
        this.detailUserService = detailUserService;
    }

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping("/getAllClients")
    public ModelAndView getAllUserClients(HttpServletRequest req) {
        HttpSession session = req.getSession();
        SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) context.getAuthentication().getPrincipal();
        ru.edu.cas.user.dao.User daoUser = userService.getUser(user.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClients(daoUser.getId()));
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
        Client client = service.createClient(name, type, inn, ogrn, segment);
        String message = "Клиент создан";
        String jsp = "/success.jsp";
        if (client == null) {
            message = "Поля должны быть заполнены!";
            jsp = "/failed.jsp";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
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
