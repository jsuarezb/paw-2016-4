package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.AppointmentSlot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AppointmentSlotDTO {

    @XmlElement
    private Integer id;

    @XmlElement
    private WorksInDTO worksIn;

    @XmlElement
    private Integer dayOfWeek;

    @XmlElement
    private Integer hour;

    public AppointmentSlotDTO(AppointmentSlot appointmentSlot) {
        this.id = appointmentSlot.getId();
        this.worksIn = new WorksInDTO(appointmentSlot.getWorksIn());
        this.dayOfWeek = appointmentSlot.getDayOfWeek();
        this.hour = appointmentSlot.getHour();
    }

    public AppointmentSlotDTO() {
    }
}
