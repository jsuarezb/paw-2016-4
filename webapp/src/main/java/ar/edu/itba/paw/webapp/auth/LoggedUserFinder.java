package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.params.LoginParams;

/**
 * Created by alebian on 12/10/16.
 */
public class LoggedUserFinder {
    public static Loggable getLoggedUser(final LoginParams input,
                                         final DoctorService doctorService,
                                         final PatientService patientService) {
        Loggable user;
        switch (input.type) {
            case Loggable.DOCTOR:
                user = doctorService.findByEmail(input.email);
                break;
            case Loggable.PATIENT:
                user = patientService.findByEmail(input.email);
                break;
            default:
                user = null;
                break;
        }
        return user;
    }
}
