package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.webapp.dto.DoctorDTO;
import ar.edu.itba.paw.webapp.params.PaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by alebian on 12/8/16.
 */
@Path("api/v1/doctors")
@Component
public class DoctorsController extends ApiController {
    @Autowired
    private DoctorService doctorService;

    @GET
    public Response index(@PathParam("first_name") String first_name,
                          @PathParam("last_name") String last_name,
                          @PathParam("speciality_id") Integer speciality_id,
                          @PathParam("page") Integer page) {

        List<Doctor> doctors;
        if (speciality_id != null) {
            doctors = doctorService.searchBySpeciality(speciality_id);
        } else {
            if (first_name == null && last_name == null) {
                doctors = doctorService.getAll();
            } else {
                doctors = doctorService.searchByName(first_name, last_name, PaginationHelper.INSTANCE.page(page));
            }
        }

        GenericEntity<List<DoctorDTO>> list = new GenericEntity<List<DoctorDTO>>(DoctorDTO.fromList(doctors)) {
        };
        return ok(list);
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") final Integer id) {
        final Doctor doctor = doctorService.get(id);

        if (doctor != null) {
            return ok(new DoctorDTO(doctor));
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
