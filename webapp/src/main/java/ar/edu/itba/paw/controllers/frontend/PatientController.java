package ar.edu.itba.paw.controllers.frontend;

import ar.edu.itba.paw.Patient;
import ar.edu.itba.paw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.itba.paw.controllers.api.AbstractRESTApiController;

import java.util.List;

/**
 * Created by santi698 on 23/03/16.
 */

@RequestMapping("/patients")
@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("patients");
        List<Patient> patients = patientService.getAll();
        mav.addObject("patients", patients);
        return mav;
    }
}
