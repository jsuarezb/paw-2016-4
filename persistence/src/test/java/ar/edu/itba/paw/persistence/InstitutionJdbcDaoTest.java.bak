package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Institution;
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
public class InstitutionJdbcDaoTest {

    private static final int INSTITUTION_ID = 1;
    private static final String INSTITUTION_NAME = "Clinica del Sol";

    private static final int INVALID_ID = -1;
    private static final String INVALID_NAME = "@$IO@#N)!";

    @Autowired
    private InstitutionDao iDao;

    @Test
    public void testGetAll() {
        final List<Institution> institutions = iDao.getAll();

        assertNotNull(institutions);
        assertNotEquals(0, institutions.size());
    }

    @Test
    public void testGetById() {
        final Institution institution = iDao.getById(INSTITUTION_ID);
        final Institution notAnInstitution = iDao.getById(INVALID_ID);

        assertNotNull(institution);
        assertNull(notAnInstitution);
    }

    @Test
    public void testSearchByName() {
        final List<Institution> institutions = iDao.getByName(INSTITUTION_NAME);
        final List<Institution> emptyInstitutions = iDao.getByName(INVALID_NAME);

        assertNotNull(institutions);
        assertNotEquals(0, institutions.size());

        assertNotNull(emptyInstitutions);
        assertEquals(0, emptyInstitutions.size());
    }

}
