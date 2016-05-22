package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import org.joda.time.DateTime;

import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot create(Institution institution, Doctor doctor, int dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(Doctor doctor);

    List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, DateTime week);

}
