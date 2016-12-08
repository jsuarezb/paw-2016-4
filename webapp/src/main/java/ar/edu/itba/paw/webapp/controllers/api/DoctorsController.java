package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.webapp.dto.DoctorDTO;
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
    public Response index() {
        final List<Doctor> allDoctors = doctorService.getAll();
        GenericEntity<List<DoctorDTO>> list = new GenericEntity<List<DoctorDTO>>(DoctorDTO.fromList(allDoctors)) {
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
