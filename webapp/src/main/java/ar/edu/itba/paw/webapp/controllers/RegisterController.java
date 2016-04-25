package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.webapp.forms.DoctorForm;
import ar.edu.itba.paw.webapp.forms.PatientForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by santi698 on 11/04/16.
 */

@Controller
public class RegisterController {

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public void registerPage() {
    }

    @RequestMapping(path = "/patients", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String registerPatientForm(@ModelAttribute PatientForm patientForm, HttpServletResponse response) {
        System.out.println(patientForm);
        response.addHeader("Refresh", "0;url=/");
        // Process form
        return "register";
    }

    @RequestMapping(path = "/doctors", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String registerDoctorForm(@ModelAttribute DoctorForm doctorForm, HttpServletResponse response) {
        System.out.println(doctorForm);
        response.addHeader("Refresh", "0;url=/");
        // Process form
        return "register";
    }
}
