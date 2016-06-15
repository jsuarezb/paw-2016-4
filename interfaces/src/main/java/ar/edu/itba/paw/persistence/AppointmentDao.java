package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentDao {

    //Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comments);

    Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot, LocalDateTime startDate, String comments);

    List<Appointment> getByDoctor(Doctor doctor);

    List<Appointment> getByPatient(Patient patient, int page);

    //FIXME
    List<Appointment> getAll();

    /**
     * Check if the doctor has an appointment already at the given time.
     * @param doctor Id of the doctor.
     * @param time Time of the appointment.
     * @return True if the doctor is free, else false.
     */
    boolean isDoctorAvailable(Doctor doctor, LocalDateTime time);

    /**
     * Delete the appointment.
     * @param appointmentId Id of the appointment.
     */
    boolean delete(int appointmentId);

    Appointment getByid(int appointmentId);
}
