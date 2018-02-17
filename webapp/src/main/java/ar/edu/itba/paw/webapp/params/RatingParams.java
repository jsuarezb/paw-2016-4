package ar.edu.itba.paw.webapp.params;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingParams {

    @XmlElement
    public Integer value;
}
