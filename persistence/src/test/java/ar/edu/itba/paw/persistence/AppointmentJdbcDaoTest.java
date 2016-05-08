package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql(value = "classpath:sql/fixtures.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AppointmentJdbcDaoTest {

    private static final int PATIENT_ID = 1;
    private static final int DOCTOR_ID = 2;
    private static final int SLOT_ID = 1;
    private static final long DATE_MILLI = 1462344347561L;
    private static final String COMMENT = "Test comment";

    private static final int INVALID_ID = -1;

    @Autowired
    private AppointmentJdbcDao appointmentDao;

    @Test
    public void testCreate() {
        final DateTime dateTime = new DateTime(DATE_MILLI);
        final Appointment appointment = appointmentDao.create(PATIENT_ID, DOCTOR_ID, SLOT_ID,
                dateTime, COMMENT);

        assertNotNull(appointment);
        assertEquals(PATIENT_ID, appointment.getPatientId());
        assertEquals(DOCTOR_ID, appointment.getDoctorId());
        assertEquals(SLOT_ID, appointment.getSlot().getId());
        assertEquals(DATE_MILLI, appointment.getDate().getMillis());
        assertEquals(COMMENT, appointment.getComments());
    }

    @Test
    public void testGetByPatient() {
        final List<Appointment> appointments = appointmentDao.getByPatient(PATIENT_ID);
        final List<Appointment> emptyAppointments = appointmentDao.getByPatient(INVALID_ID);

        assertNotNull(appointments);
        assertNotEquals(0, appointments.size());

        assertNotNull(emptyAppointments);
        assertEquals(0, emptyAppointments.size());
    }

    @Test
    public void testGetByDoctor() {
        final List<Appointment> appointments = appointmentDao.getByDoctor(DOCTOR_ID);
        final List<Appointment> emptyAppointments = appointmentDao.getByDoctor(INVALID_ID);

        assertNotNull(appointments);
        assertNotEquals(0, appointments.size());

        assertNotNull(emptyAppointments);
        assertEquals(0, emptyAppointments.size());
    }

}
