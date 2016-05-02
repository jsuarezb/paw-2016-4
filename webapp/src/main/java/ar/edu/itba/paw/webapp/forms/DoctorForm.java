package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Created by santi698 on 11/04/16.
 */
public class DoctorForm {
    @Length(min = 2, max = 30)
    private String name;
    @Length(min = 2, max = 30)
    private String last_name;
    @Length(min = 2, max = 40)
    private String speciality;
    @Email
    private String email;
    @Length(min = 6, max = 24)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DoctorForm{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
