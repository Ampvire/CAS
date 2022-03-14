package ru.edu.cas.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/info")
    public ModelAndView info() {
        return new ModelAndView("/user_panel.jsp");
    }

    @GetMapping("/getAllClients")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<String> clientList = new ArrayList<>();
        clientList.add("Client1");
        clientList.add("Client2");
        modelAndView.addObject("list", clientList);
        modelAndView.setViewName("/all_clients.jsp");
        return modelAndView;
    }

    @GetMapping("/getNewClients")
    public ModelAndView getNew() {
        ModelAndView modelAndView = new ModelAndView();
        List<String> clientList = new ArrayList<>();
        clientList.add("Client1");

        modelAndView.addObject("list", clientList);
        modelAndView.setViewName("/new_clients.jsp");
        return modelAndView;
    }


    @GetMapping("/getReport")
    public ModelAndView getReport() {
        ModelAndView modelAndView = new ModelAndView();
        LocalDateTime localDateTime = LocalDateTime.now();
        String report = new String();


        modelAndView.addObject("report", report);
        modelAndView.addObject("date",localDateTime);
        modelAndView.setViewName("/report.jsp");
        return modelAndView;
    }

    @GetMapping("/newClient")
    public ModelAndView info(HttpServletRequest req
            , HttpServletResponse res) {
        return new ModelAndView("/create_client.jsp");
    }

    @PostMapping("/update")
    public ModelAndView newUser(@RequestParam("name") String name,
                                @RequestParam("type") String type,
                                @RequestParam("inn") String inn,
                                @RequestParam("ogrn") String ogrn,
                                @RequestParam("segmentId") String segmentId,
                                HttpServletRequest req
            , HttpServletResponse res) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","Client");
        modelAndView.setViewName("/success_create.jsp");
        return modelAndView;
    }
}
