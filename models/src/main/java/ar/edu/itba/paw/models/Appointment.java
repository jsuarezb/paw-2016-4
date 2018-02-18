package ar.edu.itba.paw.models;

import ar.edu.itba.paw.models.builders.AppointmentBuilder;
import ar.edu.itba.paw.models.errors.InvalidCreationOfPastAppointment;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "appointments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"slot_id", "week_number", "year"})
})
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

    public Appointment(final Patient patient, final AppointmentSlot slot, final Integer weekNumber,
                       final Integer year, final String comments) {
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
        WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 4);

        return LocalDateTime.now()
                .withYear(year)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(weekFields.dayOfWeek(), slot.getDayOfWeek())
                .withHour(slot.getHour())
                .withMinute(0).withSecond(0).withNano(0);
    }

    public static boolean isPast(final Appointment appointment) {
        return isPast(appointment.getSlot(), appointment.getWeekNumber(), appointment.getYear());
    }

    public static boolean isPast(final AppointmentSlot slot, final int weekNumber, final int year) {
        final LocalDateTime now = LocalDateTime.now();
        final WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 4);

        final int currentWeekOfYear = now.get(weekFields.weekOfYear());
        final int currentWeekDay = now.get(weekFields.dayOfWeek());

        if (year < now.getYear())
            return true;

        if (year == now.getYear() && weekNumber < currentWeekOfYear)
            return true;

        if (year == now.getYear() && weekNumber == currentWeekOfYear && slot.getDayOfWeek() < currentWeekDay)
            return true;

        if (year == now.getYear() && weekNumber == currentWeekOfYear && slot.getDayOfWeek() == currentWeekDay
                && slot.getHour() <= now.getHour())
            return true;

        return false;
    }

    @XmlAttribute
    public String getComments() {
        return comments;
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    @Override
    public boolean equals(final Object o) {
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
