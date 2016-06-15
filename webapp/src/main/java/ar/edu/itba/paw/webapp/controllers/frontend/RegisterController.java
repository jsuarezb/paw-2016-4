package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.webapp.forms.DoctorForm;
import ar.edu.itba.paw.webapp.forms.PatientForm;
import ar.edu.itba.paw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by santi698 on 11/04/16.
 */

@Controller
public class RegisterController extends BaseController {
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
        Patient patient = patientService.create(patientForm.getName(),
                patientForm.getLastName(),
                patientForm.getEmail(),
                patientForm.getPassword());
        if (patient == null) {
            bindingResult.addError(new FieldError("patient", "email", "Email already registered"));
            return "register";
        }
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
