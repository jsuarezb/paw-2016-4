package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Speciality;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement
public class SpecialityDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private String name;

    public SpecialityDTO() {
    }

    public SpecialityDTO(final Speciality speciality) {
        this.id = speciality.getId();
        this.name = speciality.getName();
    }

    public static Set<SpecialityDTO> fromSet(final Set<Speciality> specialities) {
        if (specialities == null) {
            return Collections.emptySet();
        }
        return specialities.stream().map(s -> new SpecialityDTO(s)).collect(Collectors.toSet());
    }

    public static List<SpecialityDTO> fromList(final List<Speciality> specialities) {
        if (specialities == null) {
            return Collections.emptyList();
        }
        return specialities.stream().map(s -> new SpecialityDTO(s)).collect(Collectors.toList());
    }
}
