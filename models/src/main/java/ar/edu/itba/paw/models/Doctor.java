package ar.edu.itba.paw.models;

import java.util.Set;
import java.util.List;

/**
 * Created by santi698 on 24/03/16.
 */
public class Doctor {
    private final int id;
    private final String name;
    private final String last_name;
    private final Set<Speciality> specialities;
    private final String email;
    private final String password;
    private final List<DoctorPhone> phones;

    public Doctor(int id, String name, String last_name, Set<Speciality> specialities, String email, String password, List<DoctorPhone> phones) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.specialities = specialities;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public int getId() {
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

    public List<DoctorPhone> getPhones() {
        return phones;
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
                ", password='" + password + '\'' +
                '}';
    }
}
