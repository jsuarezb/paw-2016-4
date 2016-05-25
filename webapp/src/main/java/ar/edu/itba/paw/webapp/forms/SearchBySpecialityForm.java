package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lucascarmona on 24/5/16.
 */
public class SearchBySpecialityForm {

    @NotEmpty
    private String speciality;
    @NotEmpty
    private String neighborhood;
    @NotEmpty

public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public String toString() {
        return "SearchBySpecialityForm{" +
                "speciality='" + speciality + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                '}';
    }


}
