package ar.edu.itba.paw.webapp.controllers.api;


import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import ar.edu.itba.paw.webapp.controllers.api.AbstractRESTApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/doctors")
@Controller
public class DoctorController {

    private static final String DOCTOR_KEY = "doctor";
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/{id}")
    public ModelAndView show(@PathVariable final Integer id)  {
        final ModelAndView mav = new ModelAndView("doctor");

        Doctor doctor = doctorService.get(id);
        mav.addObject(DOCTOR_KEY, doctor);

        return mav;
    }

}
