package ru.edu.cas.clients_account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.service.ClientService;
import ru.edu.cas.product.dao.Application;
import ru.edu.cas.product.dao.Percent;
import ru.edu.cas.product.dao.Product;
import ru.edu.cas.product.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class ClientsAccountController {
    private ClientService service;
    private ProductService productService;
    private String inn;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping()
    public ModelAndView getAllUserClients() {
        ModelAndView modelAndView = new ModelAndView();
        inn = "33333898989";
        ClientFinance finance = service.getLastFinance(inn);
        List<String> products = service.getAllProductsByClientInn(inn);
        List<Product> banksProducts = productService.getAllProduct();
        List<Application> applicationList = productService.getApplication(service.getClient(inn));
        modelAndView.addObject("applications",applicationList);
        modelAndView.addObject("banksProducts", banksProducts);
        modelAndView.addObject("finance", finance);
        modelAndView.addObject("products", products);
        modelAndView.setViewName("/account/info.jsp");
        return modelAndView;
    }

    @GetMapping("/Кредитование")
    public ModelAndView requestLoans() {
        List<Percent> percents = productService.getPercent();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("inn", inn);
        modelAndView.addObject("percents", percents);
        modelAndView.setViewName("/account/request.jsp");
        return modelAndView;
    }

    @PostMapping("/calculation")
    public ModelAndView calculation(@RequestParam("sum") String sum,
                                    @RequestParam("years") String years) {
        Percent percent = productService.getPercentByYear(years);
        List<Integer> calculation = service.calculationLoans(sum, years, String.valueOf(percent.getPercent()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("inn", inn);
        modelAndView.addObject("percent", percent.getPercent());
        modelAndView.addObject("sum", sum);
        modelAndView.addObject("years", years);
        modelAndView.addObject("payment", calculation.get(0));
        modelAndView.addObject("loans", calculation.get(1));
        modelAndView.setViewName("/account/calculation.jsp");
        return modelAndView;
    }

    @GetMapping("/save/{inn}")
    public ModelAndView getInfo(@PathVariable("inn") String inn) {
        List<Integer> years = service.getListOfYears();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("years", years);
        modelAndView.addObject("inn", inn);
        modelAndView.setViewName("/account/create_finance.jsp");
        return modelAndView;
    }

    @PostMapping("/save/info/{inn}")
    public ModelAndView saveClientInfo(@PathVariable("inn") String inn,
                                       @RequestParam("revenue") String revenue,
                                       @RequestParam("staf") String staf,
                                       @RequestParam("costPrice") String costPrice,
                                       @RequestParam("assets") String assets,
                                       @RequestParam("reserves") String reserves,
                                       @RequestParam("year") String year,
                                       @RequestParam("profit") String profit) {

        ModelAndView modelAndView = new ModelAndView();
        ClientFinance finance = service.saveFinanceInfo(inn, revenue, staf, costPrice, assets, reserves, profit, "31-12-" + year);
        String message = "Финансовые показатели" + (finance == null ? " не заполнены" : " заполнены");
        String jsp = finance == null ? "/failed.jsp" : "/success.jsp";
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
        return modelAndView;
    }

    @PostMapping("/saveApplication")
    public ModelAndView saveApplication(@RequestParam("sum") String sum,
                                        @RequestParam("years") String years,
                                        @RequestParam("payment") String payment,
                                        @RequestParam("loans") String loans) {
        Client client = service.getClient(inn);
        Application application = productService.saveApplication(client, years, sum, payment, loans,"Кредитование");
        String message = "Заявка" + (application == null ? " не отправлена" : " отправлена");
        String jsp = application == null ? "/failed.jsp" : "/success.jsp";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
        return modelAndView;
    }

    @GetMapping("/{product}")
    public ModelAndView requestCashService(@PathVariable("product")String product) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = service.getClient(inn);
        Application application = productService.saveApplication(client, null, null, null, null,product);
        String message = "Заявка" + (application == null ? " не отправлена" : " отправлена");
        String jsp = application == null ? "/failed.jsp" : "/success.jsp";
        modelAndView.addObject("message", message);
        modelAndView.setViewName(jsp);
        return modelAndView;
    }


}
