package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.InstitutionService;
import ar.edu.itba.paw.services.SpecialityService;
import ar.edu.itba.paw.webapp.dto.InstitutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

/**
 * Created by alebian on 12/8/16.
 */
@Path("api/v1/institutions")
@Component
public class InstitutionsController extends ApiController {
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecialityService specialityService;

    @GET
    public Response index(@PathParam("page") Integer pageParam) {
        final List<Institution> allInstitutions = institutionService.getAll();
        GenericEntity<List<InstitutionDTO>> list = new GenericEntity<List<InstitutionDTO>>(InstitutionDTO.fromList(allInstitutions)) {
        };
        return ok(list);
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") final int id) {
        final Institution institution = institutionService.get(id);
        final List<Doctor> doctors = doctorService.getDoctorsByInstitution(id);
        System.out.println(doctors);
        final Set<Speciality> specialities = specialityService.getByInstitutionId(id);
        System.out.println(specialities);

        if (institution != null) {
            return ok(new InstitutionDTO(institution, doctors, specialities));
        } else {
            return notFound();
        }
    }
}
