package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Rating;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingDTO {

    @XmlElement
    public Integer value;

    public RatingDTO() {}

    public RatingDTO(final Rating rating) {
        this.value = rating.getValue();
    }

}
