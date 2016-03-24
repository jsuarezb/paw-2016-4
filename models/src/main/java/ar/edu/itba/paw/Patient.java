package ar.edu.itba.paw;

/**
 * Created by santi698 on 24/03/16.
 */
public class Patient {
    private final String name;
    private final String last_name;
    private final String email;
    private final String password;

    public Patient(final String name, final String last_name, final String email, final String password) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Patient patient = (Patient) o;

        return email.equals(patient.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
