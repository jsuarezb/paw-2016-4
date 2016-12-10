package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
public class UserDTO {
    public static UserDTO fromLoggable(final Loggable user) {
        UserDTO userDTO;
        switch (user.type()) {
            case Loggable.PATIENT:
                return new PatientDTO((Patient) user);
            case Loggable.DOCTOR:
                return new DoctorDTO((Doctor) user);
            default:
                return null;
        }
    }
}
