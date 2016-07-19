package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.PatientList;
import ar.edu.itba.paw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by agophurmuz on 7/19/16.
 */

@Path("patients")
@Component
public class PatientsController {

    @Autowired
    private PatientService patientService;

    @GET
    @Path("/")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response listPatients() {
        return Response.ok(new PatientList(patientService.getAll())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getById(@PathParam("id") final Integer id) {
        return Response.ok(patientService.get(id)).build();
    }
}
