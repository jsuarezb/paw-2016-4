package ar.edu.itba.paw.webapp.controllers.frontend;


import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController extends BaseController {

    private static final String SPECIALITIES_KEY = "specialities";
    @Autowired
    private SpecialityService specialityService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        List<Speciality> specialities = specialityService.getAll();
        mav.addObject(SPECIALITIES_KEY, specialities);

        return mav;
    }
}
