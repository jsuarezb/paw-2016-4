package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by santi698 on 23/03/16.
 */

@Controller
public class InstitutionDoctorAppointmentController {

    @RequestMapping("/institutions/{institution_id}/doctors/{doctor_id}/appointment_slots")
    public Object list(@PathVariable final int institution_id) throws MethodNotAllowedException {
        ModelAndView model = new ModelAndView("institution_doctor_appointment");

        // TODO

        return model;
    }
    
}
