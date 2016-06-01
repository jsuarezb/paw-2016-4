package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "appointment_slots")
public class AppointmentSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_slots_id_seq")
    @SequenceGenerator(sequenceName = "appointment_slots_id_seq", name = "appointment_slots_id_seq", allocationSize = 1)
    private Integer id;

    // TODO make (institution, doctor) pair a foreign key to works_in table
    @ManyToOne
    private Institution institution;

    @ManyToOne
    private Doctor doctor;

    @Column(nullable = false)
    private Integer dayOfWeek;

    @Column(nullable = false)
    private Integer hour;

    /* package */ AppointmentSlot(){ }

    public AppointmentSlot(Integer dayOfWeek, Integer hour, Institution institution, Doctor doctor) {
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.doctor = doctor;
        this.institution = institution;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getHour() {
        return hour;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentSlot that = (AppointmentSlot) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AppointmentSlot{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                ", institution=" + institution +
                '}';
    }
}
