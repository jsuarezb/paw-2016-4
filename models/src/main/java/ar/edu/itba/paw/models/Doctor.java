package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_seq")
    @SequenceGenerator(sequenceName = "doctors_id_seq", name = "doctors_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String last_name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctors_specialities", joinColumns = {
            @JoinColumn(name = "doctor_id", referencedColumnName = "id") })
    private Set<Speciality> specialities;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    /* package */ Doctor() {  }

    public Doctor(String name, String last_name,
                  Set<Speciality> specialities, String email, String password) {
        this.name = name;
        this.last_name = last_name;
        this.specialities = specialities;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return last_name;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Doctor doctor = (Doctor) o;

        return id == doctor.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", specialities='" + specialities + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
