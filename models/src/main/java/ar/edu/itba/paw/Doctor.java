package ar.edu.itba.paw;

/**
 * Created by santi698 on 24/03/16.
 */
public class Doctor {
    private final String name;
    private final String last_name;
    private final String speciality;
    private final String email;
    private final String password;

    public Doctor(String name, String last_name, String speciality, String email, String password) {
        this.name = name;
        this.last_name = last_name;
        this.speciality = speciality;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        return email.equals(doctor.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
