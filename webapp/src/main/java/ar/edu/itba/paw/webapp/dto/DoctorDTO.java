package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement()
public class DoctorDTO extends UserDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private UserDTO user;
    @XmlElement
    private Set<SpecialityDTO> specialities;
    @XmlElement
    private Set<InstitutionDTO> institutions;

    public DoctorDTO() {
    }

    public DoctorDTO(final Doctor doctor) {
        this.id = doctor.getId();
        if (doctor.getUser() != null) {
            this.user = new UserDTO(doctor.getUser());
        }
        this.specialities = SpecialityDTO.fromSet(doctor.getSpecialities());
        this.institutions = InstitutionDTO.fromWorksIn(doctor.getWorksIn());
    }

    public static List<DoctorDTO> fromList(final List<Doctor> doctors) {
        if (doctors == null) {
            return Collections.emptyList();
        }
        return doctors.stream().map(d -> new DoctorDTO(d)).collect(Collectors.toList());
    }
}
