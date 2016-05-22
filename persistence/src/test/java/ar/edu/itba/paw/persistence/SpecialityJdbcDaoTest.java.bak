package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.jdbc.SpecialityJdbcDao;
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
public class SpecialityJdbcDaoTest {

    private static final int SPECIALITY_ID = 1;
    private static final String SPECIALITY_NAME = "Clinico";

    private static final int INVALID_ID = -1;
    private static final String INVALID_SPECIALITY_NAME = "(JIN@}{I";

    @Autowired
    private SpecialityJdbcDao sDao;

    @Test
    public void testGetAll() {
        final List<Speciality> specialities = sDao.getAll();

        assertNotNull(specialities);
        assertNotEquals(0, specialities.size());
    }

    @Test
    public void testSearchByName() {
        final Speciality speciality = sDao.getByName(SPECIALITY_NAME);
        final Speciality nullSpeciality = sDao.getByName(INVALID_SPECIALITY_NAME);

        assertNotNull(speciality);
        assertEquals(SPECIALITY_NAME, speciality.getName());

        assertNull(nullSpeciality);
    }

    @Test
    public void testGetById() {
        final Speciality speciality = sDao.getById(SPECIALITY_ID);
        final Speciality nullSpeciality = sDao.getById(INVALID_ID);

        assertNotNull(speciality);
        assertEquals(SPECIALITY_ID, speciality.getId());

        assertNull(nullSpeciality);
    }

}
