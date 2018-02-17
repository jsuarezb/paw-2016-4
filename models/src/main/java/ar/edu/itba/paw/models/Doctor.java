package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_seq")
    @SequenceGenerator(sequenceName = "doctors_id_seq", name = "doctors_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctors_specialities", joinColumns = {
            @JoinColumn(name = "doctor_id", referencedColumnName = "id")})
    private Set<Speciality> specialities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<WorksIn> worksIn;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "doctor")
    private User user;

    public User getUser() {
        return user;
    }

    /* package */ Doctor() {
    }

    public Doctor(Set<Speciality> specialities, Set<WorksIn> worksIn) {
        this.specialities = specialities;
        this.worksIn = worksIn;
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

    @Override
    public boolean equals(final Object o) {
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
                ", specialities='" + specialities + '\'' +
                '}';
    }

}
