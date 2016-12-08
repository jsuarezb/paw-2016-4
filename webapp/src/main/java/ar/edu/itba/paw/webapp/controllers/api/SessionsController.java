package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.auth.Token;
import ar.edu.itba.paw.webapp.dto.TokenDTO;
import ar.edu.itba.paw.webapp.params.UserParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Response login(final UserParams input) {
        User user = userService.getByUsername(input.username);
        if (user != null && user.getPassword().equals(input.password)) {
            return ok(new TokenDTO(Token.create(user)));
        }
        return unauthorized();
    }
}
