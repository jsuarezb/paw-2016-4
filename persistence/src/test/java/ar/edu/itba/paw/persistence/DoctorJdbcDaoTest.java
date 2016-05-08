package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql(value = "classpath:sql/fixtures.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class DoctorJdbcDaoTest {

    private static final int DOCTOR_ID = 1;
    private static final String FIRST_NAME = "Juán";
    private static final String LAST_NAME = "Pérez";

    private static final int INVALID_ID = -1;
    private static final String INVALID_NAME = "(@$INJ)(!&";

    @Autowired
    private DoctorJdbcDao dDao;

    @Test
    public void testGetAll() {
        final List<Doctor> doctors = dDao.getAll();

        assertNotNull(doctors);
        assertNotEquals(0, doctors.size());
    }

    @Test
    public void testSearchByName() {
        final Doctor doctor = dDao.searchByName(FIRST_NAME, LAST_NAME);
        final Doctor nullDoctor = dDao.searchByName(INVALID_NAME, LAST_NAME);

        assertNotNull(doctor);
        assertEquals(FIRST_NAME, doctor.getName());
        assertEquals(LAST_NAME, doctor.getLastName());

        assertNull(nullDoctor);
    }

    @Test
    public void testGetById() {
        final Doctor doctor = dDao.getById(DOCTOR_ID);
        final Doctor nullDoctor = dDao.getById(INVALID_ID);

        assertNotNull(doctor);
        assertEquals(DOCTOR_ID, doctor.getId());

        assertNull(nullDoctor);
    }

}
