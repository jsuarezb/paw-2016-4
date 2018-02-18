package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.dto.PatientDTO;
import ar.edu.itba.paw.webapp.params.PatientParams;
import ar.edu.itba.paw.webapp.utils.Pair;
import ar.edu.itba.paw.webapp.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    private static final String USER_ALREADY_EXISTS = "El usuario ya existe";

    @Autowired
    private PatientService patientService;

    @POST
    public Response create(final PatientParams params) {
        final Patient existingPatient = patientService.findByEmail(params.email);

        if (existingPatient != null) {
            return badRequest(USER_ALREADY_EXISTS);
        } else {
            final Pair<Boolean, String> validation = PasswordValidator.validate(params.password, params.passwordConfirmation);

            if (!validation.getLeft()) {
                return badRequest(validation.getRight());
            }

            final Patient patient = patientService.create(params.firstName, params.lastName, params.email, params.password);

            if (patient.getId() == null) {
                return badRequest("Patient was not created");
            }

            return created(patient);
        }
    }

    @GET
    public Response index() {
        final List<Patient> allPatients = patientService.getAll();
        final GenericEntity<List<PatientDTO>> list = new GenericEntity<List<PatientDTO>>(PatientDTO.fromList(allPatients)) {
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
