package ar.edu.itba.paw.controllers;


import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/doctors")
@Controller
public class DoctorController extends AbstractRESTApiController {

    public ModelAndView list() throws MethodNotAllowedException {
        throw new MethodNotAllowedException();
	}
    
    public ModelAndView create() throws MethodNotAllowedException {
    	throw new MethodNotAllowedException();
    }

    public ModelAndView show(@PathVariable final Integer id) throws MethodNotAllowedException {
    	throw new MethodNotAllowedException();
    }

    public ModelAndView update(@PathVariable final Integer id) throws MethodNotAllowedException {
    	throw new MethodNotAllowedException();
    }
    
    public ModelAndView delete(@PathVariable final Integer id) throws MethodNotAllowedException {
    	throw new MethodNotAllowedException();
    }

}
