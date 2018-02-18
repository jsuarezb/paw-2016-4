package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.models.User;
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
    UserService userService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(final LoginParams input) {
        final User user = userService.findByEmail(Token.emailFromToken(input.email));
        if (user != null && user.getPassword().equals(input.password)) {
            return ok(new TokenDTO(Token.create(user)));
        }
        return unauthorized();
    }
}
