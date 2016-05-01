package ar.edu.itba.paw.models;

import java.util.Date;

/**
 * Created by santi698 on 24/03/16.
 */
public class Appointment {
    private final AppointmentSlot slot;
    private final Date date;
    private final String comments;

    public Appointment(AppointmentSlot slot, Date date, String comments) {
        this.slot = slot;
        this.date = date;
        this.comments = comments;
    }

    public AppointmentSlot getSlot() {
        return slot;
    }

    public Date getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        if (!slot.equals(that.slot)) return false;
        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = slot.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "slot=" + slot +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                '}';
    }
}
