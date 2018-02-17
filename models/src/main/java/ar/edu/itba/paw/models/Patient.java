package ar.edu.itba.paw.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "patients")
@XmlRootElement
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id_seq")
    @SequenceGenerator(sequenceName = "patients_id_seq", name = "patients_id_seq", allocationSize = 1)
    private Integer id;

    @OneToMany
    private List<Appointment> appointments;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "patient")
    private User user;

    public User getUser() {
        return user;
    }

    public Patient(){ }


    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return id == patient.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                '}';
    }
}
