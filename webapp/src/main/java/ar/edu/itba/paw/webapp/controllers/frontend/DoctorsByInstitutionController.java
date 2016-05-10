package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DoctorsByInstitutionController extends BaseController {

	@RequestMapping("/institutions/{institution_id}/doctors")
    public Object list(@PathVariable final int institution_id) throws MethodNotAllowedException {
    	ModelAndView model = new ModelAndView("doctors_by_institution");

        // TODO

        return model;
    }
}
