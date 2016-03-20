package ar.edu.itba.paw.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/doctors")
@Controller
public class DoctorController extends AbstractRESTApiController implements RESTApiController {

    public ModelAndView list() {
    	return null;
    }
    
    public ModelAndView create() {
    	return null;
    }

    public ModelAndView show(@PathVariable final int id) {
    	return null;
	}

    public ModelAndView update(@PathVariable final int id) {
    	return null;
    }
    
    public ModelAndView delete(@PathVariable final int id) {
    	return null;
    }

}
