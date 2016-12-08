package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by agophurmuz on 7/19/16.
 */

@Path("api/v1/patients")
@Component
public class PatientsController extends ApiController {

    @Autowired
    private PatientService patientService;

    @GET
    public Response index() {
        final List<Patient> allPatients = patientService.getAll();
        GenericEntity<List<PatientDTO>> list = new GenericEntity<List<PatientDTO>>(PatientDTO.fromList(allPatients)) {
        };
        return ok(list);
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") final Integer id) {
        final Patient patient = patientService.get(id);

        if (patient != null) {
            return ok(new PatientDTO(patient));
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
