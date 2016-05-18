package ar.edu.itba.paw.models;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_id_seq")
    @SequenceGenerator(sequenceName = "appointments_id_seq", name = "appointments_id_seq", allocationSize = 1)
    private int id;

    @OneToOne
    private Patient patient;

    //No tendria que tener un Doctor
    private Doctor doctor;

    @ManyToOne
    private AppointmentSlot slot;

    @Column(nullable = false)
    private DateTime date;

    @Column(length = 250, nullable = false)
    private String comments;

    /* package */ Appointment(){ }

    public Appointment(Integer id,
                       Patient patient,
                       Doctor doctor,
                       AppointmentSlot slot,
                       DateTime date,
                       String comments) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
        this.date = date;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentSlot getSlot() {
        return slot;
    }

    public DateTime getDate() {
        return date;
    }

    public String getComments() {
        return comments;
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
                ", date=" + date +
                ", comments='" + comments + '\'' +
                '}';
    }
}
