package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.Institution;
import ar.edu.itba.paw.exceptions.ResourceNotFoundException;
import ar.edu.itba.paw.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by santi698 on 23/03/16.
 */

@Controller
@RequestMapping("/institutions")
public class InstitutionController {

    private static final String INSTITUTIONS_ATTRIBUTE = "institutions";
    private static final String INSTITUTION_ATTRIBUTE = "institution";

    @Autowired
    private InstitutionService institutionService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound() {
        return "not_found_404";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("institutions");
        List<Institution> institutions = institutionService.getAll();
        modelAndView.addObject(INSTITUTIONS_ATTRIBUTE, institutions);
        return modelAndView;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable final Integer id) {
        ModelAndView modelAndView = new ModelAndView("institution_detail");
        Institution institution = institutionService.get(id);
        if (institution == null)
            throw new ResourceNotFoundException();

        modelAndView.addObject(INSTITUTION_ATTRIBUTE, institution);
        return modelAndView;
    }

}
