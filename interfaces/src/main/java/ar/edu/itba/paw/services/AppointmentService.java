package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;

import java.util.List;

public interface AppointmentService {

    Appointment create(final Patient patient,
                       final Doctor doctor,
                       final AppointmentSlot appointmentSlot,
                       final Integer weekNumber, final Integer year,
                       final String comment);

    List<Appointment> getByDoctor(final Doctor doctor);

    List<Appointment> getByPatient(final Patient patient);

    List<Appointment> getAvailableByDoctor(final Doctor doctor, final Integer weekNumber, final Integer year);

    List<Appointment> getAvailableByDoctorInInstitution(final Doctor doctor,
                                                        final Institution institution,
                                                        final Integer weekNumber,
                                                        final Integer year);

    //FIXME
    List<Appointment> getAll();
    /**
     * Cancel an appointment.
     * @param appointmentId Id of the appointment.
     * @return Amount of appointments deleted.
     */
    boolean cancel(final int appointmentId);

    List<Appointment> getAvailableBySpecialityInInstitution(final Speciality speciality,
                                                            final Institution institution,
                                                            final Integer weekNumber,
                                                            final Integer year);

    List<Appointment> getAvailableBySpeciality(final Speciality speciality,
                                               final Integer weekNumber,
                                               final Integer year);

    List<Appointment> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                              final String neiborhood,
                                                              final Integer weekNumber,
                                                              final Integer year);
}
