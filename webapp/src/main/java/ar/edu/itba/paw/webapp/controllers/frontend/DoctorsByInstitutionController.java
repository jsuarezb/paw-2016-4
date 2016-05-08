package ar.edu.itba.paw.webapp.controllers.frontend;

import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.webapp.controllers.MethodNotAllowedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DoctorsByInstitutionController extends BaseController {

	@RequestMapping("/institutions/{institution_id}/doctors")
    public Object list(@PathVariable final int institution_id) throws MethodNotAllowedException {
    	ModelAndView model = new ModelAndView("doctors_by_institution");
        
        List<Doctor> doctors = new ArrayList<Doctor>();
        Doctor doctor1 = null;
        Doctor doctor2 = null;
        Doctor doctor3 = null;
        Address address;
        Institution institution = null;
        
        if(institution_id==1){
            doctor1 = new Doctor(1, "Juan", "Perez", "Traumatologo", "juanperez@gmail.com", "juan123");
            doctor2 = new Doctor(2, "Carlos", "Lopez", "Cardiologo", "carloslopez@gmail.com", "carlitos");
            doctor3 = new Doctor(3, "Pedro", "Garcia", "Neurologo", "pedrogarcia@gmail.com", "123pedrito321");
            address = new Address("Paraguay", 1465, "7°A", "CABA", "Bs.As.", "Argentina");
            institution = new Institution(institution_id,"Grupo Medico Vertebral", address);
        }
        else if(institution_id==2){
            doctor1 = new Doctor(4, "Jose", "Martinez", "Urologo", "josemartinez@gmail.com", "pepemartinez");
            doctor2 = new Doctor(5, "Matias", "Hernandez", "Pediatra", "matiashernandez@gmail.com", "matihernandez");
            doctor3 = new Doctor(6, "Felipe", "Marquez", "Otorrinolaringologo", "felipemarquez@gmail.com", "felipeotorrino");
            address = new Address("Cerviño", 3356, "", "CABA", "Bs.As.", "Argentina");
            institution = new Institution(institution_id,"Hospital Fernandez", address);
        }
        else{
        	model.addObject("wrongId", institution_id);
        }

        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
 
        model.addObject("doctors", doctors);
        model.addObject("institution", institution);
        return model;
    }
}
