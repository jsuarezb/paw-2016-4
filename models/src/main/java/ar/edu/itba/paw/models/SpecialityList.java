package ar.edu.itba.paw.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */
@XmlRootElement
public class SpecialityList {

    public List<Speciality> list;

    public SpecialityList(List<Speciality> specialityList) {
        this.list = specialityList;
    }

    /* default */ SpecialityList(){}

    public List<Speciality> getList() {
        return list;
    }

    public void setList(List<Speciality> specialityList) {
        this.list = specialityList;
    }
}
