package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentDao;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import ar.edu.itba.paw.persistence.DoctorDao;
import ar.edu.itba.paw.persistence.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
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

    @Autowired
    private MailService mailService;

    public Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot,
                              LocalDateTime startDate, String comment) {
        if (!appointmentDao.isDoctorAvailable(doctor, startDate))
            return null;

        Appointment appointment = appointmentDao.create(patient, doctor, appointmentSlot, startDate, comment);
        if (appointment != null) {
            mailService.sendAppointmentConfirmationToDoctor(appointment, doctor, patient);
            mailService.sendAppointmentConfirmationToPatient(appointment, doctor, patient);
        }

        return appointment;
    }

    public List<Appointment> getByDoctor(Doctor doctor) {
        return appointmentDao.getByDoctor(doctor);
    }

    public List<Appointment> getByPatient(Patient patient) {
        return appointmentDao.getByPatient(patient, 0);
    }


    public List<Appointment> getAvailableByDoctor(final Doctor doctor, final LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctor(doctor, weekStart);

        for (AppointmentSlot slot : availableSlots) {
            LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableByDoctorInInstitution(Doctor doctor, Institution institution, LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctorInInstitution(doctor.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentDao.getAll();
    }

    public List<Appointment> getAvailableBySpecialityInInstitution(Speciality speciality, Institution institution, LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityInInstitution(speciality.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableBySpeciality(Speciality speciality, LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpeciality(speciality.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }


    public List<Appointment> getAvailableBySpecialityAndNeighborhood(Speciality speciality, String neiborhood, LocalDateTime weekStart){
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityAndNeighborhood(speciality, neiborhood, weekStart);

        for (AppointmentSlot slot : availableSlots) {
            LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;

    }

    public boolean cancel(int appointmentId) {
        return appointmentDao.delete(appointmentId);
    }

}
