package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;

import java.time.DayOfWeek;
import java.util.List;

public interface AppointmentSlotDao {

    AppointmentSlot create(int institutionId, int doctorId, DayOfWeek dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(int doctorId);

    List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId);

}
