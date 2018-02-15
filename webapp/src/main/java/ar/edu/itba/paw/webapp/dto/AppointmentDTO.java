package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Appointment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class AppointmentDTO {

    @XmlElement
    private Integer id;

    @XmlElement
    private PatientDTO patient;

    @XmlElement
    private AppointmentSlotDTO appointmentSlot;

    @XmlElement
    private LocalDateTime date;

    @XmlElement
    private String comments;

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.patient = new PatientDTO(appointment.getPatient());
        this.appointmentSlot = new AppointmentSlotDTO(appointment.getSlot());
        this.date = appointment.getDate();
        this.comments = appointment.getComments();
    }

    public AppointmentDTO() {
    }

    public static List<AppointmentDTO> fromList(final List<Appointment> appointments) {
        if (appointments == null) {
            return Collections.emptyList();
        }
        return appointments.stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }
}
