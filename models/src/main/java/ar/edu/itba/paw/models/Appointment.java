package ar.edu.itba.paw.models;

import ar.edu.itba.paw.models.builders.AppointmentBuilder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "appointments")
@XmlRootElement
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_id_seq")
    @SequenceGenerator(sequenceName = "appointments_id_seq", name = "appointments_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private AppointmentSlot slot;

    @Column(nullable = false, name = "week_number")
    private Integer weekNumber;

    @Column(nullable = false)
    private Integer year;

    @Column(length = 250, nullable = false)
    private String comments;

    /* package */ Appointment(){ }

    public Appointment(Patient patient, AppointmentSlot slot, Integer weekNumber, Integer year, String comments) {
        this.patient = patient;
        this.slot = slot;
        this.weekNumber = weekNumber;
        this.year = year;
        this.comments = comments;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public Patient getPatient() {
        return patient;
    }

    public AppointmentSlot getSlot() {
        return slot;
    }

    @XmlAttribute
    public Integer getWeekNumber() {
        return weekNumber;
    }

    @XmlAttribute
    public Integer getYear() {
        return year;
    }

    public LocalDateTime getDate() {
        WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 7);
        return LocalDateTime.now()
                            .withYear(year)
                            .with(weekFields.weekOfYear(), weekNumber)
                            .withHour(slot.getHour());
    }

    @XmlAttribute
    public String getComments() {
        return comments;
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", slot=" + slot +
                ", weekNumber=" + weekNumber +
                ", year=" + year +
                ", comments='" + comments + '\'' +
                '}';
    }
}
