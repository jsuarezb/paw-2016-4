package ar.edu.itba.paw.models;
import org.joda.time.DateTime;

public class Appointment {
    private final int id;
    private final int patientId;
    private final int doctorId;
    private final AppointmentSlot slot;
    private final DateTime date;
    private final String comments;

    public Appointment(int id, int patientId, int doctorId, AppointmentSlot slot, DateTime date, String comments) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.slot = slot;
        this.date = date;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public AppointmentSlot getSlot() {
        return slot;
    }

    public DateTime getDate() {
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

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", slot=" + slot +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                '}';
    }
}
