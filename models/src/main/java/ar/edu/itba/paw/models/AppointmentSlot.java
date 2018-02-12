package ar.edu.itba.paw.models;

import ar.edu.itba.paw.models.builders.AppointmentSlotBuilder;

import javax.persistence.*;

@Entity
@Table(name = "appointment_slots")
public class AppointmentSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_slots_id_seq")
    @SequenceGenerator(sequenceName = "appointment_slots_id_seq", name = "appointment_slots_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "works_in_id")
    private WorksIn worksIn;

    @Column(nullable = false)
    private Integer dayOfWeek;

    @Column(nullable = false)
    private Integer hour;

    /* package */ AppointmentSlot(){ }

    public AppointmentSlot(Integer dayOfWeek, Integer hour, WorksIn worksIn) {
        this.worksIn = worksIn;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
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

    public WorksIn getWorksIn() {
        return worksIn;
    }

    public static AppointmentSlotBuilder builder() {
        return new AppointmentSlotBuilder();
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
                ", worksIn=" + worksIn +
                ", dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                '}';
    }
}
