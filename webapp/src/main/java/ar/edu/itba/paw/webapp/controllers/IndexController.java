package ar.edu.itba.paw.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.services.UserService;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
