package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.models.WorksIn;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(final WorksIn worksIn,
                           final int dayOfWeek,
                           final int startHour);

    AppointmentSlot getById(final int id);

    List<AppointmentSlot> getByDoctor(final Doctor doctor);

    /**
     * Get all the specified doctor available slots for a specified week.
     * @param doctor Id of the doctor.
     * @param week Start of the 7 days period to search slots for.
     * @return A list of available slots.
     */
    List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor,
                                               final LocalDateTime week);

    /**
     * Get all the slots for a doctor in a single institution
     * @param doctorId Id of the doctor.
     * @param institutionId Id of the institution.
     * @return A list of slots for a doctor in an institution.
     */

    List<AppointmentSlot> getAvailableByDoctorInInstitution(final int doctorId,
                                                            final int institutionId,
                                                            final LocalDateTime weekStart);

    List<AppointmentSlot> getAvailableBySpecialityInInstitution(final int speciality_id,
                                                                final int institution_id,
                                                                final LocalDateTime week);

    List<AppointmentSlot> getAvailableBySpeciality(final int speciality_id,
                                                   final LocalDateTime week);

    List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                                  final String neiborhood,
                                                                  final LocalDateTime weekStart);
}
