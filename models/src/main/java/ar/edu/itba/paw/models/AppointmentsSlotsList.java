package ar.edu.itba.paw.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by agophurmuz on 7/17/16.
 */

@XmlRootElement
public class AppointmentsSlotsList {

    public List<Appointment> list;

    public AppointmentsSlotsList(final List<Appointment> list){
        this.list = list;
    }

    /* default */ AppointmentsSlotsList(){}

    @XmlAttribute
    public List<Appointment> getList() {
        return list;
    }

    public void setList(final List<Appointment> list) {
        this.list = list;
    }
}
