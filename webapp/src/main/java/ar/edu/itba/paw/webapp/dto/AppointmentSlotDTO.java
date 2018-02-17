package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.AppointmentSlot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public AppointmentSlotDTO(final AppointmentSlot appointmentSlot) {
        this.id = appointmentSlot.getId();
        this.worksIn = new WorksInDTO(appointmentSlot.getWorksIn());
        this.dayOfWeek = appointmentSlot.getDayOfWeek();
        this.hour = appointmentSlot.getHour();
    }

    public AppointmentSlotDTO() {
    }

    public static List<AppointmentSlotDTO> fromList(final List<AppointmentSlot> slots) {
        if (slots == null)
            return Collections.emptyList();

        return slots.stream().map(AppointmentSlotDTO::new).collect(Collectors.toList());
    }
}
