package ru.edu.cas.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.dao.ClientReport;
import ru.edu.cas.client.service.ClientService;
import ru.edu.cas.clients_account.dao.AccountClient;
import ru.edu.cas.clients_account.service.AccountClientService;
import ru.edu.cas.product.dao.Application;
import ru.edu.cas.product.service.ProductService;
import ru.edu.cas.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/user/client")
public class ClientController {
    private ClientService service;
    private AccountClientService accountClientService;
    private UserService userService;
    private ProductService productService;
    private int id;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAccountClientService(AccountClientService accountClientService) {
        this.accountClientService = accountClientService;
    }

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping("/getAllClients")
    public ModelAndView getAllUserClients(HttpServletRequest req) {
//        HttpSession session = req.getSession();
//        SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
//        User user = (User) context.getAuthentication().getPrincipal();
//        ru.edu.cas.user.dao.User daoUser = userService.getUser(user.getUsername());
        id = 6;
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("list", service.getAllClients(daoUser.getId()));
        modelAndView.addObject("list", service.getAllClients(id));

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
        String message = client == null ? "Поля должны быть заполнены!" : "Клиент изменен.";
        String jsp = client == null ? "/failed.jsp" : "/success.jsp";
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
        String message = client == null ? "Поля должны быть заполнены!" : "Клиент изменен.";
        String jsp = client == null ? "/failed.jsp" : "/success.jsp";
        if (client != null) {
            AccountClient accountClient = accountClientService.getAccount(client);
            accountClient.setPassword(password);
            accountClientService.save(accountClient);
        }
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
        return modelAndView;
    }

    @GetMapping("/application")
    public ModelAndView getAllApplications() {
        ModelAndView modelAndView = new ModelAndView();
        List<Client> clients = service.getAllClients(id);
        List<String> result = Arrays.asList("Согласовано","Отказано");
        List<Application> applicationList = productService.getApplicationByClient(clients);
        modelAndView.addObject("applications", applicationList);
        modelAndView.addObject("results", result);
        modelAndView.setViewName("/client/application.jsp");
        return modelAndView;
    }

    @PostMapping("/result")
    public ModelAndView saveResult(@RequestParam("result") String result,
                                   @RequestParam("id") String id,
                                   @RequestParam("reason") String reason){
        ModelAndView modelAndView = new ModelAndView();

        Application application = productService.getApplicationById(Integer.parseInt(id));
        application.setStatus(result);
        if (!Objects.equals(reason, "")){
            application.setRejectReason(reason);
        }
       if (result.equals("Согласовано")){
           service.createClientProduct(application.getClientId().getId(),application.getProductId().getId());
       }

        modelAndView.addObject("message", result);
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }
}
