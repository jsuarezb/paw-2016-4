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
@XmlRootElement
public class UserDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement
    private String password;
    @XmlElement
    private String phone;
    @XmlElement
    private Integer patientId;
    @XmlElement
    private Integer doctorId;

    public UserDTO() {
    }

    public UserDTO(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        final Patient patient = user.getPatient();
        final Doctor doctor = user.getDoctor();
        this.patientId = patient != null ? patient.getId() : null;
        this.doctorId = doctor != null ? doctor.getId() : null;
    }

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
