package ar.edu.itba.paw.models;

import java.time.DayOfWeek;

/**
 * Created by santi698 on 24/03/16.
 */
public class AppointmentSlot {
    private final int id;
    private final DayOfWeek dayOfWeek;
    private final Integer hour;
    private final int institutionId;
    private final int doctorId;

    public AppointmentSlot(int id, DayOfWeek dayOfWeek, Integer hour, int institutionId, int doctorId) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.doctorId = doctorId;
        this.institutionId = institutionId;
    }

    public int getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getHour() {
        return hour;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentSlot that = (AppointmentSlot) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AppointmentSlot{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                ", institutionId=" + institutionId +
                '}';
    }
}
