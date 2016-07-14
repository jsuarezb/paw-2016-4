package ar.edu.itba.paw.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */
@XmlRootElement
public class SpecialityList {

    public List<Speciality> specialityList;

    public SpecialityList(List<Speciality> specialityList) {
        this.specialityList = specialityList;
    }

    /* default */ SpecialityList(){}

    public List<Speciality> getSpecialityList() {
        return specialityList;
    }

    public void setSpecialityList(List<Speciality> specialityList) {
        this.specialityList = specialityList;
    }
}
