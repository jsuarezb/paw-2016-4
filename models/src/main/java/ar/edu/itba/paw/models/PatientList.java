package ar.edu.itba.paw.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by agophurmuz on 7/19/16.
 */
@XmlRootElement
public class PatientList {

    public List<Patient> list;

    public PatientList(final List<Patient> patientList) {
        this.list = patientList;
    }

    /* default */ PatientList(){}

    public List<Patient> getList() {
        return list;
    }

    public void setList(final List<Patient> patientList) {
        this.list = patientList;
    }
}
