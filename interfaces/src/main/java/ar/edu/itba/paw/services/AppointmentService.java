package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot,
                       LocalDateTime startDate, String comment);

    List<Appointment> getByDoctor(Doctor doctor);

    List<Appointment> getByPatient(Patient patient);

    List<Appointment> getAvailableByDoctor(Doctor doctor, LocalDateTime from);

    List<Appointment> getAvailableByDoctorInInstitution(Doctor doctor, Institution institution, LocalDateTime weekStart);

    //FIXME
    List<Appointment> getAll();
    /**
     * Cancel an appointment.
     * @param appointmentId Id of the appointment.
     * @return Amount of appointments deleted.
     */
    boolean cancel(int appointmentId);

    List<Appointment> getAvailableBySpecialityInInstitution(Speciality speciality, Institution institution, LocalDateTime weekStart);

    List<Appointment> getAvailableBySpeciality(Speciality speciality, LocalDateTime weekStart);

    List<Appointment> getAvailableBySpecialityAndNeighborhood(Speciality speciality, String neiborhood, LocalDateTime weekStart);

}
