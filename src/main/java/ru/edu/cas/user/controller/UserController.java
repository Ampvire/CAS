package ru.edu.cas.user.controller;

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
import ru.edu.cas.product.dao.Application;
import ru.edu.cas.product.service.ProductService;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private ClientService service;
    private UserService userService;
    private AccountClientService accountClientService;
    private ProductService productService;

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


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication == null ? null : authentication.getName();
        return userService.getUser(currentPrincipalName);
    }


    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }


    @GetMapping("/info")
    public ModelAndView info() {
        User user = getCurrentUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", service.getAllClients(user.getId()));
        modelAndView.addObject("segments",service.getListSegments());
        modelAndView.setViewName("/user/user_panel.jsp");
        return modelAndView;
    }


//    @GetMapping("/getAllClients")
//    public ModelAndView getAllUserClients() {
//        User user = getCurrentUser();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("list", service.getAllClients(user.getId()));
//        modelAndView.setViewName("/client/all_clients.jsp");
//        return modelAndView;
//    }

    @GetMapping("/getNewClients")
    public ModelAndView getNew(@RequestParam("segment") String segment) {
        User user = getCurrentUser();
        ClientSegment clientSegment = service.getSegment(segment);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("segment", segment);
        modelAndView.addObject("list", service.getAllClientsWithoutUser(segment));
        modelAndView.addObject("check", service.chekUserCategory(clientSegment, user));
        modelAndView.setViewName("/client/new_clients.jsp");
        return modelAndView;
    }


    @PostMapping("/addManager")
    public ModelAndView addManager(@RequestParam("inn") String inn) {
        Client client = service.getClient(inn);
        User user = getCurrentUser();
        ModelAndView modelAndView = new ModelAndView();
        service.addManager(client, user);
        modelAndView.setViewName("/client/new_clients.jsp");
        return modelAndView;
    }

    @GetMapping("/newClient")
    public ModelAndView clientInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/create_client.jsp");
        modelAndView.addObject("segments", service.getListSegments());
        modelAndView.addObject("types", service.getListTypes());
        return modelAndView;
    }

    @PostMapping("/create/success")
    public ModelAndView newUser(@RequestParam("name") String name,
                                @RequestParam("type") String type,
                                @RequestParam("inn") String inn,
                                @RequestParam("ogrn") String ogrn,
                                @RequestParam("segment") String segment) {
        Client client = service.createClient(name, type, inn, ogrn, segment);
        String message = client == null ? "Ошибка: поля должны быть заполнены!" : "Клиент создан.";
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
        modelAndView.addObject("segments", service.getListSegments());
        modelAndView.addObject("types", service.getListTypes());
        return modelAndView;
    }

    @PostMapping("/updateClient/success")
    public ModelAndView updateUpdateSuccess(@RequestParam("name") String name,
                                            @RequestParam("type") String type,
                                            @RequestParam("inn") String inn,
                                            @RequestParam("ogrn") String ogrn,
                                            @RequestParam("segment") String segment) {
        Client client = service.createClient(name, type, inn, ogrn, segment);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("segments", service.getListSegments());
        String message = client == null ? "Поля должны быть заполнены!" : "Клиент изменен.";
        String jsp = client == null ? "/failed.jsp" : "/success.jsp";
        if (client != null) {
            AccountClient accountClient = accountClientService.getAccount(client);
            accountClientService.save(accountClient);
        }
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
        return modelAndView;
    }

    @GetMapping("/application")
    public ModelAndView getAllApplications() {
        ModelAndView modelAndView = new ModelAndView();
        List<Client> clients = service.getAllClients(getCurrentUser().getId());
        List<String> result = Arrays.asList("Согласовано", "Отказано");
        List<Application> applicationList = productService.getApplicationByClient(clients);
        modelAndView.addObject("segments",service.getListSegments());
        modelAndView.addObject("applications", applicationList);
        modelAndView.addObject("results", result);
        modelAndView.setViewName("/client/application.jsp");
        return modelAndView;
    }

    @PostMapping("/result")
    public ModelAndView saveResult(@RequestParam("result") String result,
                                   @RequestParam("id") String id,
                                   @RequestParam("reason") String reason) {
        ModelAndView modelAndView = new ModelAndView();

        Application application = productService.getApplicationById(Integer.parseInt(id));
        application.setStatus(result);
        if (!Objects.equals(reason, "")) {
            application.setRejectReason(reason);
        }
        if (result.equals("Заявка согласована")) {
            service.createClientProduct(application.getClientId().getId(), application.getProductId().getId());
        }

        modelAndView.addObject("message", result);
        modelAndView.setViewName("/success.jsp");
        return modelAndView;
    }

}
