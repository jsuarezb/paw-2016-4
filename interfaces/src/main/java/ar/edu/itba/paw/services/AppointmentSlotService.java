package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot create(int institutionId, int doctorId, DayOfWeek dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(int doctorId);

    List<AppointmentSlot> getAvailableByDoctor(int doctorId, Date week);

    List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId);

}
