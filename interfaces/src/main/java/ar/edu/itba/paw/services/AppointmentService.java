package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import org.joda.time.DateTime;

import java.util.List;

public interface AppointmentService {

    Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot,
                       DateTime startDate, String comment);

    List<Appointment> getByDoctor(Doctor doctor);

    List<Appointment> getByPatient(Patient patient);

    List<Appointment> getAvailableByDoctor(Doctor doctor, DateTime weekStart);

    List<Appointment> getAvailableByDoctorInInstitution(Doctor doctor, Institution institution, DateTime weekStart);

    /**
     * Cancel an appointment.
     * @param appointmentId Id of the appointment.
     * @return Amount of appointments deleted.
     */
    boolean cancel(int appointmentId);

    List<Appointment> getAvailableBySpecialityInInstitution(Speciality speciality, Institution institution, DateTime weekStart);

    List<Appointment> getAvailableBySpeciality(Speciality speciality, DateTime weekStart);
}
