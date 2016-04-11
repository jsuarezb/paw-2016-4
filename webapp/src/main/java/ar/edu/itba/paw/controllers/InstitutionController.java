package ar.edu.itba.paw.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.Address;
import ar.edu.itba.paw.Institution;

/**
 * Created by santi698 on 23/03/16.
 */

@RequestMapping("/institutions")
@Controller
public class InstitutionController extends AbstractRESTApiController {
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	@Override
	public Object list() throws MethodNotAllowedException {
		ModelAndView model = new ModelAndView("institutions");
		ArrayList<Institution> institutions = new ArrayList<Institution>();
		institutions.add(new Institution("Clinica del Sol", new Address("Av. Coronel Diaz", 2211, "", "CABA", "Bs As", "Argentina")));
		institutions.add(new Institution("Hospital Fernandez", new Address("Cerviño", 3356, "", "CABA", "Bs As", "Argentina")));
		model.addObject("institutions", institutions);
		return model;
	}
}
