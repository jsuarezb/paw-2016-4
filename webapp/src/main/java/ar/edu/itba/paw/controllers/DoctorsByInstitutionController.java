package ar.edu.itba.paw.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.Address;
import ar.edu.itba.paw.Doctor;
import ar.edu.itba.paw.Institution;

@RequestMapping("/institutions/{institution_id}/doctors")
@Controller
public class DoctorsByInstitutionController extends AbstractRESTApiController {

	@Override
    public Object list() throws MethodNotAllowedException {
        ModelAndView model = new ModelAndView("doctors_by_institution");
        
        List<Doctor> doctors = new ArrayList<Doctor>();
        
        Doctor doctor1 = new Doctor("Juan", "Perez", "Traumatologo", "juanperez@gmail.com", "juan123");
        Doctor doctor2 = new Doctor("Carlos", "Lopez", "Cardiologo", "carloslopez@gmail.com", "carlitos");
        Doctor doctor3 = new Doctor("Pedro", "Garcia", "Neurologo", "pedrogarcia@gmail.com", "123pedrito321");
        
        Address address = new Address("Paraguay", 1465, "7°A", "CABA", "Bs.As.", "Argentina");
        Institution institution = new Institution("Grupo Medico Vertebral", address);

        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
 
        model.addObject("doctors", doctors);
        model.addObject("institution", institution);
        return model;
    }
}
