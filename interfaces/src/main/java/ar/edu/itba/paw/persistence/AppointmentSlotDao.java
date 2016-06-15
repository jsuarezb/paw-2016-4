package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.models.WorksIn;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(WorksIn worksIn, int dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(Doctor doctor);

    /**
     * Get all the specified doctor available slots for a specified week.
     * @param doctor Id of the doctor.
     * @param week Start of the 7 days period to search slots for.
     * @return A list of available slots.
     */
    List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, LocalDateTime week);

    /**
     * Get all the slots for a doctor in a single institution
     * @param doctorId Id of the doctor.
     * @param institutionId Id of the institution.
     * @return A list of slots for a doctor in an institution.
     */

    List<AppointmentSlot> getAvailableByDoctorInInstitution(int doctorId, int institutionId, LocalDateTime weekStart);

    List<AppointmentSlot> getAvailableBySpecialityInInstitution(int speciality_id, int institution_id, LocalDateTime week);

    List<AppointmentSlot> getAvailableBySpeciality(int speciality_id, LocalDateTime week);

    List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(Speciality speciality, String neiborhood, LocalDateTime weekStart);
}
