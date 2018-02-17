package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.models.errors.InvalidCreationOfPastAppointment;
import ar.edu.itba.paw.persistence.AppointmentDao;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private AppointmentSlotDao slotDao;

    @Autowired
    private MailService mailService;

    public Appointment create(final Patient patient, final Doctor doctor,
                              final AppointmentSlot appointmentSlot,
                              final Integer weekNumber, final Integer year,
                              final String comment) {
        if (!appointmentDao.isDoctorAvailable(appointmentSlot, weekNumber, year))
            return null;
        final LocalDate today = LocalDate.now();
        int currentWeekOfYear = today.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        if (weekNumber < currentWeekOfYear) {
            throw new InvalidCreationOfPastAppointment();
        }
        if (weekNumber == currentWeekOfYear && appointmentSlot.getDayOfWeek() <= today.getDayOfWeek().getValue()) {
            throw new InvalidCreationOfPastAppointment();
        }
        final Appointment appointment = appointmentDao.create(patient, doctor, appointmentSlot, weekNumber, year, comment);

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


    public List<Appointment> getAvailableByDoctor(final Doctor doctor,
                                                  final Integer weekNumber,
                                                  final Integer year) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctor(doctor, weekNumber, year);

        for (AppointmentSlot slot : availableSlots) {
            final Appointment appointment = new Appointment(null, slot, weekNumber, year, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableByDoctorInInstitution(final Doctor doctor,
                                                               final Institution institution,
                                                               final Integer weekNumber,
                                                               final Integer year) {
        final List<Appointment> appointments = new ArrayList<>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableByDoctorInInstitution(doctor.getId(), institution.getId(), weekNumber, year);

        for (AppointmentSlot slot : availableSlots) {
            final Appointment appointment = new Appointment(null, slot, weekNumber, year, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentDao.getAll();
    }

    @Override
    public PagedResult<Appointment> search(final Integer weekOfYear, final Integer year,
                                           final Integer institutionId, final Integer specialityId,
                                           final String neighborhood, final Integer doctorId,
                                           final int page) {
        final PagedResult<AppointmentSlot> slots =
                slotDao.search(weekOfYear, year, institutionId, specialityId, neighborhood, doctorId, page);

        final List<Appointment> appointment = slots.getResults().stream()
                .map(slot -> Appointment.builder()
                        .setWeek(weekOfYear, year)
                        .setPatient(null)
                        .setSlot(slot)
                        .build())
                .collect(Collectors.toList());

        return new PagedResult<>(appointment, page, slots.getPageSize(), slots.getTotal());
    }

    public List<Appointment> getAvailableBySpecialityInInstitution(final Speciality speciality,
                                                                   final Institution institution,
                                                                   final Integer weekNumber,
                                                                   final Integer year) {
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityInInstitution(speciality.getId(), institution.getId(), weekNumber, year);

        for (AppointmentSlot slot : availableSlots) {
            final Appointment appointment = new Appointment(null, slot, weekNumber, year, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    public List<Appointment> getAvailableBySpeciality(final Speciality speciality,
                                                      final Integer weekNumber,
                                                      final Integer year) {
        final List<Appointment> appointments = new ArrayList<>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpeciality(speciality.getId(), weekNumber, year);

        for (AppointmentSlot slot : availableSlots) {
            final Appointment appointment = new Appointment(null, slot, weekNumber, year, null);
            appointments.add(appointment);
        }

        return appointments;
    }


    public List<Appointment> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                                     final String neiborhood,
                                                                     final Integer weekNumber,
                                                                     final Integer year){
        final List<Appointment> appointments = new ArrayList<Appointment>();
        final List<AppointmentSlot> availableSlots = slotDao
                .getAvailableBySpecialityAndNeighborhood(speciality, neiborhood, weekNumber, year);

        for (AppointmentSlot slot : availableSlots) {
            final Appointment appointment = new Appointment(null, slot, weekNumber, year, null);
            appointments.add(appointment);
        }

        return appointments;

    }

    @Override
    public List<Appointment> getIncomingAppointments(final Doctor doctor) {
        final LocalDateTime now = LocalDateTime.now();

        return appointmentDao.getIncomingAppointments(doctor, now);
    }

    @Override
    public List<Appointment> getPastAppointments(final Doctor doctor) {
        final LocalDateTime now = LocalDateTime.now();

        return appointmentDao.getPastAppointments(doctor, now);
    }

    public boolean cancel(final int appointmentId) {
        final Appointment appointment = appointmentDao.getByid(appointmentId);
        final LocalDateTime now = LocalDateTime.now();
        final int week = now.get(WeekFields.of(DayOfWeek.SUNDAY, 7).weekOfYear());
        if (appointment.getYear() < now.getYear()) {
            return false;
        }
        if (appointment.getWeekNumber() < week && appointment.getYear() == now.getYear()) {
            return false;
        }

        if (appointment.getWeekNumber() == week &&
                appointment.getSlot().getDayOfWeek() <= now.getDayOfWeek().getValue()) {
            return false;
        }
        final Doctor doctor = appointment.getSlot().getWorksIn().getDoctor();
        final Patient patient = appointment.getPatient();

        final boolean success = appointmentDao.delete(appointmentId);

        if(success){
            mailService.sendAppointmentCancellationToDoctor(appointment, doctor, patient);
            mailService.sendAppointmentCancellationToPatient(appointment, doctor, patient);
        }
        return success;

    }

    /* package */ void setAppointmentDao(final AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    /* package */ void setSlotDao(final AppointmentSlotDao slotDao) {
        this.slotDao = slotDao;
    }

    /* package */ void setMailService(final MailService mailService) {
        this.mailService = mailService;
    }
}
