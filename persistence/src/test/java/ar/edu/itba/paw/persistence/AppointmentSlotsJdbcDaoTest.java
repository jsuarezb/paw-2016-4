package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AppointmentSlotsJdbcDaoTest {

    private static final String TABLE_NAME = "AppointmentSlots";
    private static final int INSTITUTION_ID = 1;
    private static final int DOCTOR_ID = 79;
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.SUNDAY;
    private static final Integer START_HOUR = 14;

    @Autowired
    private DataSource ds;

    @Autowired
    private AppointmentSlotJdbcDao dao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, TABLE_NAME);
    }

    @Test
    public void testCreate() {
        final AppointmentSlot slot = dao.create(INSTITUTION_ID, DOCTOR_ID, DAY_OF_WEEK, START_HOUR);

        assertNotNull(slot);
        assertEquals(INSTITUTION_ID, slot.getInstitutionId());
        assertEquals(DOCTOR_ID, slot.getDoctorId());
        assertEquals(DAY_OF_WEEK, slot.getDayOfWeek());
        assertEquals(START_HOUR, slot.getHour());
    }

    @Test
    public void testGetById() {
        final AppointmentSlot aSlot = dao.create(INSTITUTION_ID, DOCTOR_ID, DAY_OF_WEEK, START_HOUR);
        int id = aSlot.getId();

        final AppointmentSlot slot = dao.getById(id);
        assertNotNull(slot);
        assertEquals(id, slot.getId());
    }

    @Test
    public void testGetByDoctor() {
        dao.create(INSTITUTION_ID, DOCTOR_ID, DAY_OF_WEEK, START_HOUR);

        final List<AppointmentSlot> list = dao.getByDoctor(DOCTOR_ID);
        assertNotNull(list);
        assertNotEquals(0, list.size());
    }

    @Test
    public void testGetByDoctorInInstitution() {
        dao.create(INSTITUTION_ID, DOCTOR_ID, DAY_OF_WEEK, START_HOUR);

        final List<AppointmentSlot> list = dao.getByDoctorInInstitution(DOCTOR_ID, INSTITUTION_ID);
        assertNotNull(list);
        assertNotEquals(0, list.size());
    }

}
