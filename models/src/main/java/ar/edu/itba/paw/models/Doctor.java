package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor implements Loggable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_seq")
    @SequenceGenerator(sequenceName = "doctors_id_seq", name = "doctors_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctors_specialities", joinColumns = {
            @JoinColumn(name = "doctor_id", referencedColumnName = "id")})
    private Set<Speciality> specialities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<WorksIn> worksIn;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @SuppressWarnings("unused")
    /* package */ Doctor() {
    }

    public Doctor(Integer id, String name, String lastName, Set<Speciality> specialities,
                  Set<WorksIn> worksIn, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.specialities = specialities;
        this.email = email;
        this.password = password;
        this.worksIn = worksIn;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<WorksIn> getWorksIn() {
        return worksIn;
    }

    public Integer getId() {
        return id;
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
                ", lastName='" + lastName + '\'' +
                ", specialities='" + specialities + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public String type() {
        return Loggable.DOCTOR;
    }
}
