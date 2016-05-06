package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    @Autowired
    private AppointmentSlotDao appointmentSlotDao;

    public AppointmentSlot create(int institutionId, int doctorId, DayOfWeek dayOfWeek, int startHour) {
        return appointmentSlotDao.create(institutionId, doctorId, dayOfWeek, startHour);
    }

    public AppointmentSlot getById(int id) {
        return appointmentSlotDao.getById(id);
    }

    public List<AppointmentSlot> getByDoctor(int doctorId) {
        return appointmentSlotDao.getByDoctor(doctorId);
    }

    public List<AppointmentSlot> getAvailableByDoctor(int doctorId, Date week) {
        return appointmentSlotDao.getAvailableByDoctor(doctorId, week);
    }

    public List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId) {
        return appointmentSlotDao.getByDoctorInInstitution(doctorId, institutionId);
    }
}
