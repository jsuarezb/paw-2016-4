package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentDao {
    Appointment create(final Patient patient, final Doctor doctor,
                       final AppointmentSlot appointmentSlot,
                       final Integer weekNumber, final Integer year,
                       final String comments);

    List<Appointment> getByDoctor(final Doctor doctor);

    List<Appointment> getByPatient(final Patient patient, final int page);

    //FIXME
    List<Appointment> getAll();

    /**
     * Check if the doctor has an appointment already at the given time.
     * @param doctor Id of the doctor.
     * @param weekNumber Week of year of the appointment.
     * @param year Year of the appointment.
     * @return True if the doctor is free, else false.
     */
    boolean isDoctorAvailable(final AppointmentSlot appointmentSlot, final Integer weekNumber, final Integer year);

    /**
     * Delete the appointment.
     * @param appointmentId Id of the appointment.
     */
    boolean delete(final int appointmentId);

    Appointment getByid(final int appointmentId);

    List<Appointment> getIncomingAppointments(Doctor doctor, LocalDateTime now);

    List<Appointment> getPastAppointments(Doctor doctor, LocalDateTime now);
}
