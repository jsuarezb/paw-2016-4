package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import org.joda.time.DateTime;

import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(Institution institution, Doctor doctor, int dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(Doctor doctor);

    /**
     * Get all the specified doctor available slots for a specified week.
     * @param doctor Id of the doctor.
     * @param week Start of the 7 days period to search slots for.
     * @return A list of available slots.
     */
    List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, DateTime week);

    /**
     * Get all the slots for a doctor in a single institution
     * @param doctorId Id of the doctor.
     * @param institutionId Id of the institution.
     * @return A list of slots for a doctor in an institution.
     */

    List<AppointmentSlot> getAvailableByDoctorInInstitution(int doctorId, int institutionId, DateTime weekStart);

    List<AppointmentSlot> getAvailableBySpecialityInInstitution(int speciality_id, int institution_id, DateTime week);

    List<AppointmentSlot> getAvailableBySpeciality(int speciality_id, DateTime week);

}
