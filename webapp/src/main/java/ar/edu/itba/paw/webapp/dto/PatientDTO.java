package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Patient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement
public class PatientDTO extends UserDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private String first_name;
    @XmlElement
    private String last_name;
    @XmlElement
    private String email;
    @XmlElement
    private String type;

    public PatientDTO() {
    }

    public PatientDTO(final Patient patient) {
        if (patient == null)
            return;

        this.id = patient.getId();
        this.first_name = patient.getName();
        this.last_name = patient.getLastName();
        this.email = patient.getEmail();
        this.type = patient.type();
    }

    public static List<PatientDTO> fromList(final List<Patient> patients) {
        if (patients == null) {
            return Collections.emptyList();
        }
        return patients.stream().map(d -> new PatientDTO(d)).collect(Collectors.toList());
    }
}
