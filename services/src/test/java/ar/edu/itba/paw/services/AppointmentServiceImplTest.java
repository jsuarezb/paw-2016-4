package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentDao;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class AppointmentServiceImplTest {

   /* private AppointmentServiceImpl service;

    private Institution institution =
            new Institution("Inst name", new Address("", 0, "", "","","",""), 2);

    private Doctor doctor =
            new Doctor(1, "Name", "Last name", Collections.emptySet(),
                    Collections.emptySet(), "email", "password");

    private Patient patient =
            new Patient("A name", "A last name", "An email", "A password");

    private AppointmentSlot appoinmentSlot =
            new AppointmentSlot(2, 4, new WorksIn(1, doctor, institution));

    private List<AppointmentSlot> slots = Collections.singletonList(appoinmentSlot);

    private LocalDateTime dateTime = LocalDateTime.of(2017, 10, 10, 2, 3);

    private Appointment appointment = new Appointment(patient, appoinmentSlot, 3, 2018, "");

    private List<Appointment> appointments = Collections.singletonList(appointment);

    private Speciality speciality = new Speciality(1, "spec");

    @Mock
    private AppointmentDao appointmentDao;

    @Mock
    private AppointmentSlotDao slotDao;

    @Mock
    private MailService mailService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new AppointmentServiceImpl();
        service.setAppointmentDao(appointmentDao);
        service.setMailService(mailService);
        service.setSlotDao(slotDao);
    }

    @Test
    public void testCreateWithUnavailableDoctor() {
        when(appointmentDao.isDoctorAvailable(eq(appoinmentSlot), anyInt(), anyInt())).thenReturn(false);

        final Appointment appointment = service.create(patient, doctor, appoinmentSlot, 3, 2018, "");
        assertNull(appointment);
    }

    @Test
    public void testGetByDoctor() {
        when(appointmentDao.getByDoctor(any(Doctor.class))).thenReturn(appointments);

        assertArrayEquals(appointments.toArray(), service.getByDoctor(doctor).toArray());
    }

    @Test
    public void testGetByPatient() {
        when(appointmentDao.getByPatient(any(Patient.class), anyInt())).thenReturn(appointments);

        assertArrayEquals(appointments.toArray(), service.getByPatient(patient).toArray());
    }

    @Test
    public void testGetAvailableByDoctor() {
        when(slotDao.getAvailableByDoctor(any(Doctor.class), anyInt(), anyInt())).thenReturn(slots);

        assertEquals(slots.size(), service.getAvailableByDoctor(doctor, 24, 2018).size());
    }

    @Test
    public void testGetAvailableByDoctorInInstitution() {
        when(slotDao.getAvailableByDoctorInInstitution(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(slots);

        assertEquals(slots.size(), service.getAvailableByDoctorInInstitution(doctor, institution, 30, 2017).size());
    }

    @Test
    public void testGetAll() {
        when(appointmentDao.getAll()).thenReturn(appointments);

        assertEquals(appointments, service.getAll());
    }

    @Test
    public void testGetAvailableBySpecialityInInstitution() {
        when(slotDao.getAvailableBySpecialityInInstitution(anyInt(), anyInt(), anyInt(), anyInt()))
                .thenReturn(slots);

        assertEquals(
                slots.size(),
                service.getAvailableBySpecialityInInstitution(speciality, institution, 30, 2018).size());
    }

    @Test
    public void testGetAvailableBySpeciality() {
        when(slotDao.getAvailableBySpeciality(any(Integer.class), anyInt(), anyInt())).thenReturn(slots);

        assertEquals(slots.size(), service.getAvailableBySpeciality(speciality, 23, 2017).size());
    }

    @Test
    public void testGetAvailableBySpecialityAndNeighborhood() {
        when(slotDao.getAvailableBySpecialityAndNeighborhood(
                    any(Speciality.class), anyString(), anyInt(), anyInt()))
                .thenReturn(slots);

        assertEquals(slots.size(),
                service.getAvailableBySpecialityAndNeighborhood(speciality, "", 2, 2018).size());
    }

    @Test
    public void testCancelWhenAppointmentIsInTheFuture() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime futureDate = now.plusWeeks(1);

        final Appointment futureAppointment = new Appointment(patient, appoinmentSlot,
                futureDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()),
                futureDate.getYear(), "");

        when(appointmentDao.getByid(anyInt())).thenReturn(futureAppointment);
        when(appointmentDao.delete(anyInt())).thenReturn(true);

        assertTrue(service.cancel(1));
    }

    @Test
    public void testCancelWhenAppointmentIsInThePast() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime past = now.minusMonths(1);

        final Appointment pastAppointment = new Appointment(patient, appoinmentSlot,
                past.get(WeekFields.of(DayOfWeek.SUNDAY, 4).weekOfYear()),
                past.getYear(), "");

        when(appointmentDao.getByid(anyInt())).thenReturn(pastAppointment);
        when(appointmentDao.delete(anyInt())).thenReturn(true);

        assertFalse(service.cancel(1));
    }*/
}
