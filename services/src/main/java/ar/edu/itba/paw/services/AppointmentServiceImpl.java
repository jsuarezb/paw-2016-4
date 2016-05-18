package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentDao;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import ar.edu.itba.paw.persistence.DoctorDao;
import ar.edu.itba.paw.persistence.PatientDao;
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

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;

    public Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot,
                              DateTime startDate, String comment) {
        if (!appointmentDao.isDoctorAvailable(doctor, startDate))
            return null;
        return appointmentDao.create(patient, doctor, appointmentSlot, startDate, comment);
    }

    public List<Appointment> getByDoctor(Doctor doctor) {
        return appointmentDao.getByDoctor(doctor);
    }

    public List<Appointment> getByPatient(Patient patient) {
        return appointmentDao.getByPatient(patient, 0);
    }

    public List<Appointment> getAvailableByDoctor(final Doctor doctor, final DateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctor(doctor.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            DateTime appointmentTime = weekStart
                    .withDayOfWeek(DateTimeConstants.MONDAY)
                    .withTime(0, 0, 0, 0)
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, doctor, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
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

            Appointment appointment = new Appointment(null, doctor, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableBySpecialityInInstitution(Speciality speciality, Institution institution, DateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityInInstitution(speciality.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            DateTime appointmentTime = weekStart
                    .withDayOfWeek(DateTimeConstants.MONDAY)
                    .withTime(0, 0, 0, 0)
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot.getDoctor(), slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableBySpeciality(Speciality speciality, DateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpeciality(speciality.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            DateTime appointmentTime = weekStart
                    .withDayOfWeek(DateTimeConstants.MONDAY)
                    .withTime(0, 0, 0, 0)
                    .withHourOfDay(slot.getHour())
                    .withDayOfWeek(slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot.getDoctor(), slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public boolean cancel(int appointmentId) {
        return appointmentDao.delete(appointmentId);
    }
}
