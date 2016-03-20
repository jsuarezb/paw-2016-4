package ar.edu.itba.paw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.services.UserService;

@Controller
public class HelloWorldController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index() {

        final ModelAndView mav = new ModelAndView("register");
        userService.register("juan", "secreto");
        return mav;
    }

    @RequestMapping("/users/{username}")
    public ModelAndView getUsers(@PathVariable final String username) {
        final ModelAndView mav = new ModelAndView("user");
        User user = userService.getByUsername(username);
        if (user != null) {
        	mav.addObject("user", user);
        }
        return mav;
    }
}
