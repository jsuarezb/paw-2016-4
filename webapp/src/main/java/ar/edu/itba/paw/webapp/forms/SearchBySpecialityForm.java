package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by lucascarmona on 24/5/16.
 */
public class SearchBySpecialityForm {

    @NotNull
    private Integer specialityId;

    @NotEmpty
    private String neighborhood;

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer speciality_id) {
        this.specialityId = speciality_id;
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
                "specialityId='" + specialityId + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                '}';
    }


}
