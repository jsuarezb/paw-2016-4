package ar.edu.itba.paw.webapp.controllers.api;

import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.dto.UserDTO;
import ar.edu.itba.paw.webapp.params.UserParams;
import ar.edu.itba.paw.webapp.utils.Pair;
import ar.edu.itba.paw.webapp.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alebian on 12/8/16.
 */
@Path("api/v1/users")
@Component
public class UsersController extends ApiController {

    @Autowired
    private UserService userService;

    @GET
    @Path("/me")
    public Response me() {
        System.out.println(getLoggedUser().type());
        return ok(UserDTO.fromLoggable(getLoggedUser()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final UserParams input) {
        final User existUser = userService.getByUsername(input.username);
        if (existUser != null) {
            return badRequest(USER_DOES_NOT_EXIST);
        } else {
            final Pair<Boolean, String> validation = PasswordValidator.validate(input.password, input.passwordConfirmation);

            if (!validation.getLeft()) {
                return badRequest(validation.getRight());
            }

            final User user = userService.register(input.username, input.password);
            if (user.getId() == null) {
                return badRequest("User already exists.");
            }
            return created(user);
        }
    }
}
