package ar.edu.itba.paw.models;

import java.time.DayOfWeek;

/**
 * Created by santi698 on 24/03/16.
 */
public class AppointmentSlot {
    private final DayOfWeek dayOfWeek;
    private final Integer hour;
    private final Institution institution;

    public AppointmentSlot(DayOfWeek dayOfWeek, Integer hour, Institution institution) {
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.institution = institution;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getHour() {
        return hour;
    }

    public Institution getInstitution() {
        return institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentSlot that = (AppointmentSlot) o;

        if (dayOfWeek != that.dayOfWeek) return false;
        if (!hour.equals(that.hour)) return false;
        return institution.equals(that.institution);

    }

    @Override
    public int hashCode() {
        int result = dayOfWeek.hashCode();
        result = 31 * result + hour.hashCode();
        result = 31 * result + institution.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AppointmentSlot{" +
                "dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                ", institution=" + institution +
                '}';
    }
}
