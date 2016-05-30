package ar.edu.itba.paw.models;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_id_seq")
    @SequenceGenerator(sequenceName = "appointments_id_seq", name = "appointments_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private AppointmentSlot slot;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 250, nullable = false)
    private String comments;

    /* package */ Appointment(){ }

    public Appointment(Patient patient, AppointmentSlot slot, LocalDateTime date, String comments) {
        this.patient = patient;
        this.slot = slot;
        this.date = date;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public AppointmentSlot getSlot() {
        return slot;
    }

    public LocalDateTime getDate() {
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
