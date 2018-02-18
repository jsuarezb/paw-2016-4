package ar.edu.itba.paw.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ratings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"doctor_id", "patient_id"})
})
@XmlRootElement
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratings_id_seq")
    @SequenceGenerator(sequenceName = "ratings_id_seq", name = "ratings_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @Column(nullable = false)
    private Integer value;

    /* package */ Rating() {}

    public Rating(final Doctor doctor, final Patient patient, final Integer value) {
        this.doctor = doctor;
        this.patient = patient;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Integer getValue() {
        return value;
    }
}
