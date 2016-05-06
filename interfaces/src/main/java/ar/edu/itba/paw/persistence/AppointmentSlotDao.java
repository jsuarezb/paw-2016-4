package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(int institutionId, int doctorId, DayOfWeek dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(int doctorId);

    /**
     * Get all the specified doctor available slots for a specified week.
     * @param doctorId Id of the doctor.
     * @param week Start of the 7 days period to search slots for.
     * @return A list of available slots.
     */
    List<AppointmentSlot> getAvailableByDoctor(int doctorId, Date week);

    List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId);

}
