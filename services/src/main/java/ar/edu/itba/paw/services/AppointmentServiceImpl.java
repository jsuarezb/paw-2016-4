package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentDao;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private AppointmentSlotDao slotDao;

    public Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comment) {
        if (!appointmentDao.isDoctorAvailable(doctorId, startDate))
            return null;

        return appointmentDao.create(patientId, doctorId, slotId, startDate, comment);
    }

    public List<Appointment> getByDoctor(int doctorId) {
        return appointmentDao.getByDoctor(doctorId);
    }

    public List<Appointment> getByPatient(int patientId) {
        return appointmentDao.getByPatient(patientId, 0);
    }

    public List<Appointment> getAvailableByDoctorInInstitution(Doctor doctor, Institution institution, DateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctorInInstitution(doctor.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            DateTime appointmentTime = weekStart
                    .withDayOfWeek(DateTimeConstants.MONDAY)
                    .withTime(0, 0, 0, 0)
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek());

            Appointment appointment = new Appointment(0, null, doctor, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getBySpecialityInInstitution(Speciality speciality, Institution institution, DateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getBySpecialityInInstitution(speciality.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            DateTime appointmentTime = weekStart
                    .withDayOfWeek(DateTimeConstants.MONDAY)
                    .withTime(0, 0, 0, 0)
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek());

            Appointment appointment = new Appointment(0, null, slot.getDoctor(), slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getBySpeciality(Speciality speciality, DateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getBySpeciality(speciality.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            DateTime appointmentTime = weekStart
                    .withDayOfWeek(DateTimeConstants.MONDAY)
                    .withTime(0, 0, 0, 0)
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek());

            Appointment appointment = new Appointment(0, null, slot.getDoctor(), slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public int cancel(int appointmentId) {
        return appointmentDao.delete(appointmentId);
    }
}
