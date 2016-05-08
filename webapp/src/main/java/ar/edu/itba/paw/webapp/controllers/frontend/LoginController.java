package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginController extends BaseController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute LoginForm loginForm) {
        return "login";
    }
}
