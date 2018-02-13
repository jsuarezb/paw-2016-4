package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment create(final Patient patient,
                       final Doctor doctor,
                       final AppointmentSlot appointmentSlot,
                       final LocalDateTime startDate,
                       final String comment);

    List<Appointment> getByDoctor(final Doctor doctor);

    List<Appointment> getByPatient(final Patient patient);

    List<Appointment> getAvailableByDoctor(final Doctor doctor, final LocalDateTime from);

    List<Appointment> getAvailableByDoctorInInstitution(final Doctor doctor,
                                                        final Institution institution,
                                                        final LocalDateTime weekStart);

    //FIXME
    List<Appointment> getAll();

    List<Appointment> search(final Integer institution_id, final String neighborhood,
                             final Integer speciality_id, final String firstName, final String lastName, final int page);

    /**
     * Cancel an appointment.
     * @param appointmentId Id of the appointment.
     * @return Amount of appointments deleted.
     */
    boolean cancel(final int appointmentId);

    List<Appointment> getAvailableBySpecialityInInstitution(final Speciality speciality,
                                                            final Institution institution,
                                                            final LocalDateTime weekStart);

    List<Appointment> getAvailableBySpeciality(final Speciality speciality,
                                               final LocalDateTime weekStart);

    List<Appointment> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                              final String neiborhood,
                                                              final LocalDateTime weekStart);
}
