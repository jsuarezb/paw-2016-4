package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.webapp.forms.DoctorForm;
import ar.edu.itba.paw.webapp.forms.PatientForm;
import ar.edu.itba.paw.services.PatientService;
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
public class RegisterController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public void registerPage(@ModelAttribute PatientForm patientForm) {
    }

    @RequestMapping(path = "/patients", method = RequestMethod.POST)
    public String registerPatientForm(@ModelAttribute @Valid PatientForm patientForm,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        patientService.create(patientForm.getName(),
                              patientForm.getLastName(),
                              patientForm.getEmail(),
                              patientForm.getPassword());
        // Process form
        return "redirect:/";
    }

    @RequestMapping(path = "/doctors", method = RequestMethod.POST)
    public String registerDoctorForm(@ModelAttribute @Valid DoctorForm doctorForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        // Process form
        return "redirect:/";
    }
}
