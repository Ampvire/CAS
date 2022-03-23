package ru.edu.cas.clients_account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.service.ClientService;

import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class ClientsAccountController {
    private ClientService service;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping()
    public ModelAndView getAllUserClients() {
        ModelAndView modelAndView = new ModelAndView();
        List<ClientFinance> finance = service.getAllFinanceByClientInn("503613265245");
        modelAndView.addObject("finance", finance.get(0));
        modelAndView.setViewName("/account/info.jsp");
        return modelAndView;
    }

    @GetMapping("/loans/{inn}")
    public ModelAndView requestLoans(@PathVariable("inn") String inn) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("inn", inn);
        modelAndView.setViewName("/account/request.jsp");
        return modelAndView;
    }

    @PostMapping("/loans/calculation/{inn}")
    public ModelAndView calculation(@PathVariable("inn") String inn,
                                    @RequestParam("sum") String sum,
                                    @RequestParam("years") String years,
                                    @RequestParam("percent") String percent) {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> calculation = service.calculationLoans(sum, years, percent);

        List<ClientFinance> finance = service.getAllFinanceByClientInn(inn);
        modelAndView.addObject("sum", sum);
        modelAndView.addObject("years", years);
        modelAndView.addObject("percent", percent);
        modelAndView.addObject("payment", calculation.get(0));
        modelAndView.addObject("loans", calculation.get(1));
        modelAndView.setViewName("/account/calculation.jsp");
        return modelAndView;
    }

    @GetMapping("/save/{inn}")
    public ModelAndView getInfo(@PathVariable("inn") String inn) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("inn", inn);
        modelAndView.setViewName("/account/create_finance.jsp");
        return modelAndView;
    }
    @PostMapping("/info/{inn}")
    public ModelAndView saveClientInfo(@PathVariable("inn") String inn,
                                       @RequestParam("revenue") String revenue,
                                       @RequestParam("staf") String staf,
                                       @RequestParam("costPrice") String costPrice,
                                       @RequestParam("assets") String assets,
                                       @RequestParam("reserves") String reserves,
                                       @RequestParam("profit") String profit) {
        ModelAndView modelAndView = new ModelAndView();
        service.saveFinanceInfo(inn,revenue,staf,costPrice,assets,reserves,profit);
        modelAndView.setViewName("success.jsp");
        return modelAndView;
    }
}
