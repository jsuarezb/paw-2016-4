package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.params.LoginParams;

/**
 * Created by alebian on 12/10/16.
 */
public class LoggedUserFinder {
    public static Loggable getLoggedUser(final LoginParams input, final UserService userService) {
        return userService.findByEmail(input.email);
    }
}
