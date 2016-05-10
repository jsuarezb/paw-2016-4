package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by agophurmuz on 5/9/16.
 */
public class InstitutionDetailController extends BaseController{

    private final static String INSTITUTION_KEY = "institution";

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping("/institutions/{institution_id}")
    public ModelAndView list(
            @PathVariable final Integer institution_id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") DateTime weekDate) {
        ModelAndView model = new ModelAndView("institution_detail");

        Institution institution = institutionService.get(institution_id);
        if (institution == null)
            throw new ResourceNotFoundException();

        model.addObject(INSTITUTION_KEY, institution);

        return model;
    }
}
