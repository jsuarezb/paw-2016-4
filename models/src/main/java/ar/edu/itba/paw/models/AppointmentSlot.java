package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "appointment_slots")
public class AppointmentSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_slots_id_seq")
    @SequenceGenerator(sequenceName = "appointment_slots_id_seq", name = "appointment_slots_id_seq", allocationSize = 1)
    private int id;

    @OneToOne
    private Institution institution;

    @OneToOne
    private Doctor doctor;

    @Column(nullable = false)
    private int dayOfWeek;

    @Column(nullable = false)
    private int hour;

    /* package */ AppointmentSlot(){ }

    public AppointmentSlot(int id, int dayOfWeek, int hour, Institution institution, Doctor doctor) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.doctor = doctor;
        this.institution = institution;
    }

    public int getId() {
        return id;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getHour() {
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
