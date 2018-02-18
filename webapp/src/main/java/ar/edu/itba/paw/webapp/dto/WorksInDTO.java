package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.models.WorksIn;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorksInDTO {

    @XmlElement
    private Integer id;

    @XmlElement
    private DoctorDTO doctor;

    @XmlElement
    private InstitutionDTO institution;

    public WorksInDTO(final WorksIn worksIn) {
        this.id = worksIn.getId();
        this.doctor = new DoctorDTO(worksIn.getDoctor());
        this.institution = new InstitutionDTO(worksIn.getInstitution());
    }

    public WorksInDTO() {
    }
}
