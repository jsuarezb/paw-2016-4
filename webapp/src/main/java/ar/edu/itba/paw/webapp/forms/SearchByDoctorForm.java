package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lucascarmona on 24/5/16.
 */
public class SearchByDoctorForm {

    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "SearchByDoctorForm{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
