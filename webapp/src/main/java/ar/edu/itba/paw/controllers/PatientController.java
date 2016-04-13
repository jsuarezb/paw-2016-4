package ar.edu.itba.paw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.itba.paw.controllers.AbstractRESTApiController;

/**
 * Created by santi698 on 23/03/16.
 */

@RequestMapping("/patients")
@Controller
public class PatientController extends AbstractRESTApiController {

	public ModelAndView create() throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

	public String list(Model model) throws MethodNotAllowedException {
		//model.addAttribute("patients", { new Patient()});
		return "patients";
	}

	public ModelAndView delete() throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

	public ModelAndView update() throws MethodNotAllowedException {
		throw new MethodNotAllowedException();
	}

}
