package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.models.Speciality;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement()
public class InstitutionDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private String name;
    @XmlElement
    private AddressDTO address;
    @XmlElement
    private List<DoctorDTO> doctors;
    @XmlElement
    private Set<SpecialityDTO> specialities;

    public InstitutionDTO() {
    }

    public InstitutionDTO(final Institution institution) {
        this(institution, null, null);
    }

    public InstitutionDTO(final Institution institution, final List<Doctor> doctors) {
        this(institution, doctors, null);
    }

    public InstitutionDTO(final Institution institution, final List<Doctor> doctors, Set<Speciality> specialities) {
        this.id = institution.getId();
        this.name = institution.getName();
        this.address = new AddressDTO(institution.getAddress());
        this.doctors = DoctorDTO.fromList(doctors);
        this.specialities = SpecialityDTO.fromSet(specialities);
    }

    public static List<InstitutionDTO> fromList(List<Institution> institutions) {
        return institutions.stream().map(i -> new InstitutionDTO(i)).collect(Collectors.toList());
    }
}
