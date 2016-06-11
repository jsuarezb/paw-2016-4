package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.models.WorksIn;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot create(WorksIn worksIn, int dayOfWeek, int startHour);

    AppointmentSlot getById(int id);

    List<AppointmentSlot> getByDoctor(Doctor doctor);

    List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, LocalDateTime week);

}
