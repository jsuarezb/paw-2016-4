package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.auth.LoggedUserFinder;
import ar.edu.itba.paw.webapp.auth.Token;
import ar.edu.itba.paw.webapp.dto.TokenDTO;
import ar.edu.itba.paw.webapp.params.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alebian on 12/8/16.
 */
@Path("api/v1")
@Component
public class SessionsController extends ApiController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(final LoginParams input) {
        final Loggable user = LoggedUserFinder.getLoggedUser(input, doctorService, patientService);
        if (user != null && user.getPassword().equals(input.password)) {
            return ok(new TokenDTO(Token.create(user)));
        }
        return unauthorized();
    }
}
