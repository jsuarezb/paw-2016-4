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

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany
    private List<Appointment> appointments;

    /* package */ Patient(){ }

    public Patient(final String name,
                   final String lastName,
                   final String email,
                   final String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    @XmlAttribute
    public String getLastName() {
        return lastName;
    }

    @XmlAttribute
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public boolean equals(Object o) {
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
                ", name='" + name + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
