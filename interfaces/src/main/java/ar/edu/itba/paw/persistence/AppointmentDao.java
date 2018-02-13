package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentDao {
    Appointment create(final Patient patient, final Doctor doctor,
                       final AppointmentSlot appointmentSlot,
                       final LocalDateTime startDate,
                       final String comments);

    List<Appointment> getByDoctor(final Doctor doctor);

    List<Appointment> getByPatient(final Patient patient, final int page);

    //FIXME
    List<Appointment> getAll();

    List<Appointment> search(final Integer institution_id, final String neighborhood,
                             final Integer speciality_id, final String firstName, final String lastName, final int page);

    /**
     * Check if the doctor has an appointment already at the given time.
     * @param doctor Id of the doctor.
     * @param time Time of the appointment.
     * @return True if the doctor is free, else false.
     */
    boolean isDoctorAvailable(final Doctor doctor, final LocalDateTime time);

    /**
     * Delete the appointment.
     * @param appointmentId Id of the appointment.
     */
    boolean delete(final int appointmentId);

    Appointment getByid(final int appointmentId);
}
