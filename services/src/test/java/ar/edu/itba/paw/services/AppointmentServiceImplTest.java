package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentDao;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class AppointmentServiceImplTest {

    private AppointmentServiceImpl service;

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

    private Appointment appointment = new Appointment(patient, appoinmentSlot, dateTime, "");

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
        when(appointmentDao.isDoctorAvailable(eq(doctor), any(LocalDateTime.class))).thenReturn(false);

        final Appointment appointment = service.create(patient, doctor, appoinmentSlot, dateTime, "");
        assertNull(appointment);
    }


    @Test
    public void testCreateWithAvailableDoctor() {
        when(appointmentDao.isDoctorAvailable(eq(doctor), any(LocalDateTime.class))).thenReturn(true);
        when(appointmentDao.create(any(), any(), any(), any(), any())).thenReturn(appointment);

        final Appointment appointment = service.create(patient, doctor, appoinmentSlot, dateTime, "");
        assertNotNull(appointment);
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
        when(slotDao.getAvailableByDoctor(any(Doctor.class), any(LocalDateTime.class))).thenReturn(slots);

        assertEquals(slots.size(), service.getAvailableByDoctor(doctor, dateTime).size());
    }

    @Test
    public void testGetAvailableByDoctorInInstitution() {
        when(slotDao.getAvailableByDoctorInInstitution(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(slots);

        assertEquals(slots.size(), service.getAvailableByDoctorInInstitution(doctor, institution, dateTime).size());
    }

    @Test
    public void testGetAll() {
        when(appointmentDao.getAll()).thenReturn(appointments);

        assertEquals(appointments, service.getAll());
    }

    @Test
    public void testGetAvailableBySpecialityInInstitution() {
        when(slotDao.getAvailableBySpecialityInInstitution(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(slots);

        assertEquals(
                slots.size(),
                service.getAvailableBySpecialityInInstitution(speciality, institution, dateTime).size());
    }

    @Test
    public void testGetAvailableBySpeciality() {
        when(slotDao.getAvailableBySpeciality(any(Integer.class), any(LocalDateTime.class))).thenReturn(slots);

        assertEquals(slots.size(), service.getAvailableBySpeciality(speciality, dateTime).size());
    }

    @Test
    public void testGetAvailableBySpecialityAndNeighborhood() {
        when(slotDao.getAvailableBySpecialityAndNeighborhood(
                    any(Speciality.class), anyString(), any(LocalDateTime.class)))
                .thenReturn(slots);

        assertEquals(slots.size(),
                service.getAvailableBySpecialityAndNeighborhood(speciality, "", dateTime).size());
    }

    @Test
    public void testCancelWhenAppointmentIsInTheFuture() {
        Appointment futureAppointment = new Appointment(patient, appoinmentSlot, LocalDateTime.now().plusDays(1), "");
        when(appointmentDao.getByid(anyInt())).thenReturn(futureAppointment);
        when(appointmentDao.delete(anyInt())).thenReturn(true);

        assertTrue(service.cancel(1));
    }

    @Test
    public void testCancelWhenAppointmentIsInThePast() {
        Appointment futureAppointment = new Appointment(patient, appoinmentSlot, LocalDateTime.now().minusDays(1), "");
        when(appointmentDao.getByid(anyInt())).thenReturn(futureAppointment);
        when(appointmentDao.delete(anyInt())).thenReturn(true);

        assertFalse(service.cancel(1));
    }
}
