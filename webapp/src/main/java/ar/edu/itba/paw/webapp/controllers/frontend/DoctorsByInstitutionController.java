package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DoctorsByInstitutionController extends BaseController {

    private final static String INSTITUTION_KEY = "institution";
    private final static String DOCTORS_KEY = "doctors";

    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private DoctorService doctorService;

	@RequestMapping("/institutions/{institution_id}/doctors")
    public ModelAndView list(@PathVariable final Integer institution_id) throws MethodNotAllowedException {

        ModelAndView model = new ModelAndView("doctors_by_institution");

        Institution institution = institutionService.get(institution_id);

        if (institution == null) {
            throw new ResourceNotFoundException();
        }

        final List<Doctor> doctors = doctorService.getDoctorsByInstitution(institution_id);

        model.addObject(INSTITUTION_KEY, institution);
        model.addObject(DOCTORS_KEY, doctors);

        return model;
    }
}
