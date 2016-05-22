package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    @Autowired
    private AppointmentSlotDao appointmentSlotDao;

    public AppointmentSlot create(Institution institution, Doctor doctor, int dayOfWeek, int startHour) {
        return appointmentSlotDao.create(institution, doctor, dayOfWeek, startHour);
    }

    public AppointmentSlot getById(int id) {
        return appointmentSlotDao.getById(id);
    }

    public List<AppointmentSlot> getByDoctor(Doctor doctor) {
        return appointmentSlotDao.getByDoctor(doctor);
    }

    public List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, DateTime week) {
        return appointmentSlotDao.getAvailableByDoctor(doctor, week);
    }
}
