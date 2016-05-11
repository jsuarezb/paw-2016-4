package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.services.SpecialityService;
import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * Created by lucascarmona on 10/5/16.
 */

@Controller
public class InstitutionSpecialitiesController extends BaseController{

    private final static String INSTITUTION_KEY = "institution";
    private final static String SPECIALITIES_KEY = "specialities";

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private SpecialityService specialityService;

    @RequestMapping("/institutions/{institution_id}/specialities")
    public ModelAndView list(@PathVariable final Integer institution_id) throws MethodNotAllowedException {

        ModelAndView model = new ModelAndView("institution_specialities");

        Institution institution = institutionService.get(institution_id);

        if (institution == null) {
            throw new ResourceNotFoundException();
        }

        final Set<Speciality> specialities = specialityService.getByInstitutionId(institution_id);

        model.addObject(INSTITUTION_KEY,institution);
        model.addObject(SPECIALITIES_KEY,specialities);

        return model;
    }
}
