package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;
import org.joda.time.DateTimeConstants;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql(value = "classpath:sql/fixtures.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AppointmentSlotJdbcDaoTest {

    private static final int SLOT_ID = 1;
    private static final int INSTITUTION_ID = 1;
    private static final int DOCTOR_ID = 1;
    private static final int DAY_OF_WEEK = DateTimeConstants.FRIDAY;
    private static final int START_HOUR = 13;

    private static final int INVALID_ID = -1;

    @Autowired
    private AppointmentSlotDao asDao;

    @Test
    public void testCreate() {
        final AppointmentSlot slot = asDao.create(INSTITUTION_ID, DOCTOR_ID, DAY_OF_WEEK, START_HOUR);

        assertNotNull(slot);
        assertEquals(DAY_OF_WEEK, slot.getDayOfWeek());
        assertEquals(START_HOUR, slot.getHour());
        assertEquals(INSTITUTION_ID, slot.getInstitution());
        assertEquals(DOCTOR_ID, slot.getDoctor());
    }

    @Test
    public void testGetById() {
        final AppointmentSlot slot = asDao.getById(SLOT_ID);

        assertNotNull(slot);
        assertEquals(SLOT_ID, slot.getId());
    }

    @Test
    public void testGetByDoctor() {
        final List<AppointmentSlot> slots = asDao.getByDoctor(DOCTOR_ID);
        final List<AppointmentSlot> emptySlots = asDao.getByDoctor(INVALID_ID);

        assertNotNull(slots);
        assertNotEquals(0, slots.size());

        assertNotNull(emptySlots);
        assertEquals(0, emptySlots.size());
    }

    @Test
    @Ignore
    public void testGetAvailableByDoctor() {
        // TODO learn how to test time based tests
    }

    @Test
    public void testGetByDoctorInInstitution() {
        final List<AppointmentSlot> slots = asDao.getByDoctorInInstitution(DOCTOR_ID, INSTITUTION_ID);
        final List<AppointmentSlot> emptySlots = asDao.getByDoctorInInstitution(DOCTOR_ID, INVALID_ID);

        assertNotNull(slots);
        assertNotEquals(0, slots.size());

        assertNotNull(emptySlots);
        assertNotEquals(0, slots.size());
    }

}
