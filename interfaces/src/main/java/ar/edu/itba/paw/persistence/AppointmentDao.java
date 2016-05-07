package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.List;

public interface AppointmentDao {

    Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comments);

    List<Appointment> getByDoctor(int doctorId);

    List<Appointment> getByPatient(int patientId, int page);

    /**
     * Check if the doctor has an appointment already at the given time.
     * @param doctorId Id of the doctor.
     * @param time Time of the appointment.
     * @return True if the doctor is free, else false.
     */
    boolean isDoctorAvailable(int doctorId, DateTime time);

    /**
     * Delete the appointment.
     * @param appointmentId Id of the appointment.
     */
    int delete(int appointmentId);

}
