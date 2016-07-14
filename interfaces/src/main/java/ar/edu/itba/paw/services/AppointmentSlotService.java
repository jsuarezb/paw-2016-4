package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.WorksIn;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot create(final WorksIn worksIn,
                           final int dayOfWeek,
                           final int startHour);

    AppointmentSlot getById(final int id);

    List<AppointmentSlot> getByDoctor(final Doctor doctor);

    List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor, final LocalDateTime week);

}
