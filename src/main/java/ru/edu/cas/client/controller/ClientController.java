package ru.edu.cas.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.dao.ClientReport;
import ru.edu.cas.client.dao.ClientSegment;
import ru.edu.cas.client.service.ClientService;
import ru.edu.cas.clients_account.dao.AccountClient;
import ru.edu.cas.clients_account.service.AccountClientService;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/user/client")
public class ClientController {
    private ClientService service;
    private AccountClientService accountClientService;
    private UserService userService;

    @PostMapping(value ="/addManager")
    public ModelAndView addManager(@RequestParam("inn") String inn) {
        Client client = service.getClient(inn);
        User user = getCurrentUser();
        service.addManager(client, user);
        return getAllUserClients();
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAccountClientService(AccountClientService accountClientService) {
        this.accountClientService = accountClientService;
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication == null ? null : authentication.getName();
        return userService.getUser(currentPrincipalName);
    }


    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping("/getAllClients")
    public ModelAndView getAllUserClients() {
        User user = getCurrentUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClients(user.getId()));
        modelAndView.setViewName("/client/all_clients.jsp");
        return modelAndView;
    }

    @GetMapping("/getNewClients")
    public ModelAndView getNew(@RequestParam("segment") String segment) {
        User user = getCurrentUser();
        ClientSegment clientSegment = service.getSegment(segment);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClientsWithoutUser(segment));
        modelAndView.addObject("check", service.chekUserCategory(clientSegment, user));
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
    public ModelAndView newClient(@RequestParam("name") String name,
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
        List<String> productsName = service.getAllProductsByClientInn(inn);
        modelAndView.addObject("finances", finances);
        modelAndView.addObject("report", reports);
        modelAndView.addObject("products", productsName);
        modelAndView.addObject("date", LocalDateTime.now());
        modelAndView.setViewName("/client/report.jsp");
        return modelAndView;
    }

    @GetMapping("/updateClient/{inn}")
    public ModelAndView updateClient(@PathVariable("inn") String inn) {
        Client client = service.getClient(inn);
        AccountClient accountClient = accountClientService.getAccount(client);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/update_client.jsp");
        modelAndView.addObject("name", client.getName());
        modelAndView.addObject("inn", client.getInn());
        modelAndView.addObject("ogrn", client.getOgrn());
        modelAndView.addObject("password", accountClient.getPassword());
        modelAndView.addObject("segments", service.getListSegments());
        modelAndView.addObject("types", service.getListTypes());
        return modelAndView;
    }


    @PostMapping("/updateClient/success")
    public ModelAndView updateUpdateSuccess(@RequestParam("password") String password,
                                            @RequestParam("name") String name,
                                            @RequestParam("type") String type,
                                            @RequestParam("inn") String inn,
                                            @RequestParam("ogrn") String ogrn,
                                            @RequestParam("segment") String segment) {
        Client client = service.createClient(name, type, inn, ogrn, segment);
        ModelAndView modelAndView = new ModelAndView();
        String message = "Клиент изменен";
        String jsp = "/success.jsp";
        if (client == null) {
            message = "Поля должны быть заполнены!";
            jsp = "/failed.jsp";
        } else {
            AccountClient accountClient = accountClientService.getAccount(client);
            accountClient.setPassword(password);
            accountClientService.save(accountClient);
        }
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
        return modelAndView;
    }
}
