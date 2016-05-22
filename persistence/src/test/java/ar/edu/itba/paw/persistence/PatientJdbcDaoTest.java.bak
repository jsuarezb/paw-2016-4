package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql(value = "classpath:sql/fixtures.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PatientJdbcDaoTest {

    private static final int PATIENT_ID = 1;
    private static final String EMAIL = "jrodriguez@hotmail.com";
    private static final String INVALID_EMAIL = "(@#NJI@)EQW(I.com";

    private static final int INVALID_ID = -1;

    @Autowired
    private PatientDao pDao;

    @Test
    public void testGetAll() {
        final List<Patient> patients = pDao.getAll();

        assertNotNull(patients);
        assertNotEquals(0, patients.size());
    }

    @Test
    public void testGetById() {
        final Patient patient = pDao.getById(PATIENT_ID);
        final Patient nullPatient = pDao.getById(INVALID_ID);

        assertNotNull(patient);
        assertNull(nullPatient);
    }

    @Test
    public void testFindByEmail() {
        final Patient patient = pDao.getByEmail(EMAIL);
        final Patient nullPatient = pDao.getByEmail(INVALID_EMAIL);

        assertNotNull(patient);
        assertEquals(EMAIL, patient.getEmail());

        assertNull(nullPatient);
    }
}
