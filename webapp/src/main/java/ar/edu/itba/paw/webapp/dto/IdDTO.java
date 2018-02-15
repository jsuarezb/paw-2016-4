package ar.edu.itba.paw.webapp.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IdDTO {
    @XmlElement
    private Integer id;

    public IdDTO() {
    }

    public IdDTO(Integer id) {
        this.id = id;
    }
}
