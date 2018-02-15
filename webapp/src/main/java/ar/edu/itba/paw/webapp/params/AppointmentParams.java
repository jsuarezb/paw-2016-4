package ar.edu.itba.paw.webapp.params;

import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement
public class AppointmentParams {

    @XmlElement
    public int slotId;

    @XmlElement
    public Integer weekNumber;

    @XmlElement
    public Integer year;

    @XmlElement
    public String comment;

    public AppointmentParams() {
    }

}
