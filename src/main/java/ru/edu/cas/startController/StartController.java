package ru.edu.cas.startController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class StartController {

    @GetMapping
    public ModelAndView getLoginInForm() {
        return new ModelAndView("/appPage/start.jsp");
    }
}


