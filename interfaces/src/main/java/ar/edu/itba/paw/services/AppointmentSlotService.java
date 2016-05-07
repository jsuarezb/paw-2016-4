package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import org.joda.time.DateTime;

import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot create(int institutionId, int doctorId, int dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(int doctorId);

    List<AppointmentSlot> getAvailableByDoctor(int doctorId, DateTime week);

    List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId);

}
