package ar.edu.itba.paw.models;

public class AppointmentSlot {
    private final int id;
    private final Institution institution;
    private final Doctor doctor;
    private final int dayOfWeek;
    private final int hour;

    public AppointmentSlot(int id, int dayOfWeek, int hour, Institution institution, Doctor doctor) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.doctor = doctor;
        this.institution = institution;
    }

    public int getId() {
        return id;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Doctor getDoctor() {
        return doctor;
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
                ", institution=" + institution +
                '}';
    }
}
