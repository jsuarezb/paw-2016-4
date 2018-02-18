package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Patient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class PatientDTO extends UserDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private UserDTO user;

    public PatientDTO() {
    }

    public PatientDTO(final Patient patient) {
        if (patient == null)
            return;

        this.id = patient.getId();
        this.user = new UserDTO(patient.getUser());
    }

    public static List<PatientDTO> fromList(final List<Patient> patients) {
        if (patients == null) {
            return Collections.emptyList();
        }
        return patients.stream().map(d -> new PatientDTO(d)).collect(Collectors.toList());
    }
}
