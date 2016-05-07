package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;
import org.joda.time.DateTime;

import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(int institutionId, int doctorId, int dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(int doctorId);

    /**
     * Get all the specified doctor available slots for a specified week.
     * @param doctorId Id of the doctor.
     * @param week Start of the 7 days period to search slots for.
     * @return A list of available slots.
     */
    List<AppointmentSlot> getAvailableByDoctor(int doctorId, DateTime week);

    /**
     * Get all the slots for a doctor in a single institution
     * @param doctorId Id of the doctor.
     * @param institutionId Id of the institution.
     * @return A list of slots for a doctor in an institution.
     */
    List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId);

    List<AppointmentSlot> getAvailableByDoctorInInstitution(int doctorId, int institutionId, DateTime weekStart);

}
