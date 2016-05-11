package ar.edu.itba.paw.models;

import java.util.List;

/**
 * Created by santi698 on 24/03/16.
 */
public class Patient {
    private final int id;
    private final String name;
    private final String last_name;
    private final String email;
    private final String password;
    private final List<PatientPhone> phones;

    public Patient(final int id,
                   final String name,
                   final String last_name,
                   final String email,
                   final String password,
                   List<PatientPhone> phones) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<PatientPhone> getPhones() {
        return phones;
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
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
