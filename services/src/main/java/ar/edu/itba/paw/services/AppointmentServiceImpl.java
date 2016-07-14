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

    public Appointment create(final Patient patient, final Doctor doctor,
                              final AppointmentSlot appointmentSlot,
                              final LocalDateTime startDate, final String comment) {
        if (!appointmentDao.isDoctorAvailable(doctor, startDate))
            return null;

        final Appointment appointment = appointmentDao.create(patient, doctor, appointmentSlot, startDate, comment);

        if (appointment != null) {
            mailService.sendAppointmentConfirmationToDoctor(appointment, doctor, patient);
            mailService.sendAppointmentConfirmationToPatient(appointment, doctor, patient);
        }

        return appointment;
    }

    public List<Appointment> getByDoctor(final Doctor doctor) {
        return appointmentDao.getByDoctor(doctor);
    }

    public List<Appointment> getByPatient(final Patient patient) {
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

            final Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableByDoctorInInstitution(final Doctor doctor,
                                                               final Institution institution,
                                                               final LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctorInInstitution(doctor.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            final LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            final Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentDao.getAll();
    }

    public List<Appointment> getAvailableBySpecialityInInstitution(final Speciality speciality,
                                                                   final Institution institution,
                                                                   final LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityInInstitution(speciality.getId(), institution.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            final LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            final Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableBySpeciality(final Speciality speciality,
                                                      final LocalDateTime weekStart) {
        final List<Appointment> appointments = new ArrayList<>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpeciality(speciality.getId(), weekStart);

        for (AppointmentSlot slot : availableSlots) {
            final LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            final Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;
    }


    public List<Appointment> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                                     final String neiborhood,
                                                                     final LocalDateTime weekStart){
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityAndNeighborhood(speciality, neiborhood, weekStart);

        for (AppointmentSlot slot : availableSlots) {
            final LocalDateTime appointmentTime = weekStart
                    .withHour(slot.getHour())
                    .with(ChronoField.DAY_OF_WEEK, slot.getDayOfWeek());

            final Appointment appointment = new Appointment(null, slot, appointmentTime, null);
            appointments.add(appointment);
        }

        return appointments;

    }

    public boolean cancel(final int appointmentId) {
        final Appointment appointment = appointmentDao.getByid(appointmentId);
        final Doctor doctor = appointment.getSlot().getWorksIn().getDoctor();
        final Patient patient = appointment.getPatient();

        final boolean success = appointmentDao.delete(appointmentId);

        if(success){
            mailService.sendAppointmentCancellationToDoctor(appointment, doctor, patient);
            mailService.sendAppointmentCancellationToPatient(appointment, doctor, patient);
        }
        return success;

    }
}
