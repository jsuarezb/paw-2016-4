package ar.edu.itba.paw.webapp.params;

import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement
public class AppointmentParams {

    @XmlElement
    public int doctorId;

    @XmlElement
    public int slotId;

    @XmlElement
    public String startDate;

    @XmlElement
    public String comment;

    public AppointmentParams() {
    }

}
