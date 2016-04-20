package ar.edu.itba.paw.controllers.frontend;

import ar.edu.itba.paw.Patient;
import ar.edu.itba.paw.forms.LoginForm;
import ar.edu.itba.paw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by santi698 on 11/04/16.
 */
@Controller
public class LoginController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute LoginForm loginForm) {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        if (patientService.login(loginForm.getEmail(), loginForm.getPassword())) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

}
