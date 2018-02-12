package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.WorksIn;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    @Autowired
    private AppointmentSlotDao appointmentSlotDao;

    public AppointmentSlot create(final WorksIn worksIn,
                                  final int dayOfWeek,
                                  final int startHour) {
        return appointmentSlotDao.create(worksIn, dayOfWeek, startHour);
    }

    public AppointmentSlot getById(final int id) {
        return appointmentSlotDao.getById(id);
    }

    public List<AppointmentSlot> getByDoctor(final Doctor doctor) {
        return appointmentSlotDao.getByDoctor(doctor);
    }

    public List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor, final LocalDateTime week) {
        return appointmentSlotDao.getAvailableByDoctor(doctor, week);
    }

    /* package */ void setAppointmentSlotDao(AppointmentSlotDao appointmentSlotDao) {
        this.appointmentSlotDao = appointmentSlotDao;
    }
}
