package ar.edu.itba.paw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.Doctor;

@RequestMapping("/doctors/{doctor_id}")
@Controller
public class DoctorDetailController {
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public Object list() throws MethodNotAllowedException {
		ModelAndView model = new ModelAndView("doctor");
		Doctor doctor = new Doctor(1, "Juan", "Perez", 3, "juanperez@gmail.com", "juanperez");
		model.addObject("doctor", doctor);
		return model;
	}
}
