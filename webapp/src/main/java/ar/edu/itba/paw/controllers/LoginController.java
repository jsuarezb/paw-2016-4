package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by santi698 on 11/04/16.
 */
@Controller
public class LoginController {
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute LoginForm loginForm, HttpServletResponse response) {
        System.out.println(loginForm);
        response.addHeader("Refresh", "0;url=/");
        return "login";
    }

}
