package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.User;
import ar.edu.itba.paw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        mav.addObject("username", user.getUsername());
        return mav;
    }
}
