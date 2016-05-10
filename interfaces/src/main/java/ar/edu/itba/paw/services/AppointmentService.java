package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.List;

public interface AppointmentService {

    Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comment);

    List<Appointment> getByDoctor(int doctorId);

    List<Appointment> getByPatient(int patientId);

    List<Appointment> getAvailableByDoctorInInstitution(Doctor doctor, Institution institution, DateTime weekStart);

    /**
     * Cancel an appointment.
     * @param appointmentId Id of the appointment.
     * @return Amount of appointments deleted.
     */
    int cancel(int appointmentId);

    List<Appointment> getBySpecialityInInstitution(Speciality speciality, Institution institution, DateTime weekStart);

    List<Appointment> getBySpeciality(Speciality speciality, DateTime weekStart);
}
