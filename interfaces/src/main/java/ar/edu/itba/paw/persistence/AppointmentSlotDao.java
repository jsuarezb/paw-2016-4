package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.*;

import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(final WorksIn worksIn,
                           final int dayOfWeek,
                           final int startHour);

    AppointmentSlot getById(final int id);

    PagedResult<AppointmentSlot> search(final Integer weekNumber, final Integer year,
                                        final Integer institutionId, final Integer specialityId,
                                        final String neighborhood, final String firstName, final String lastName,
                                        final int page);

    List<AppointmentSlot> getByDoctor(final Doctor doctor);

    /**
     * Get all the specified doctor available slots for a specified week.
     * @param doctor Id of the doctor.
     * @param week Start of the 7 days period to search slots for.
     * @return A list of available slots.
     */
    List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor,
                                               final Integer weekNumber,
                                               final Integer year);

    /**
     * Get all the slots for a doctor in a single institution
     * @param doctorId Id of the doctor.
     * @param institutionId Id of the institution.
     * @return A list of slots for a doctor in an institution.
     */

    List<AppointmentSlot> getAvailableByDoctorInInstitution(final int doctorId,
                                                            final int institutionId,
                                                            final Integer weekNumber,
                                                            final Integer year);

    List<AppointmentSlot> getAvailableBySpecialityInInstitution(final int speciality_id,
                                                                final int institution_id,
                                                                final Integer weekNumber,
                                                                final Integer year);

    List<AppointmentSlot> getAvailableBySpeciality(final int speciality_id,
                                                   final Integer weekNumber,
                                                   final Integer year);

    List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                                  final String neiborhood,
                                                                  final Integer weekNumber,
                                                                  final Integer year);
}
