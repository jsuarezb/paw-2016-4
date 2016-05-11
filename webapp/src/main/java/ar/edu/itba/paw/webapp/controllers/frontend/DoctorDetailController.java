package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.services.AppointmentService;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import ar.edu.itba.paw.webapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;


@RequestMapping("/doctors/{doctor_id}")
@Controller
public class DoctorDetailController extends BaseController {

	private final static String DOCTOR_KEY = "doctor";

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public Object list(@PathVariable final Integer doctor_id) {

		ModelAndView model = new ModelAndView("doctor");

		final Doctor doctor = doctorService.get(doctor_id);
        if (doctor == null)
            throw new ResourceNotFoundException();

		model.addObject(DOCTOR_KEY, doctor);

		return model;
	}

}
