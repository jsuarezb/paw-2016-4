package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.PagedResult;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class DoctorServiceImplTest {

    private DoctorServiceImpl service;

    private Doctor doctor = new Doctor(Collections.emptySet(), Collections.emptySet());

    private List<Doctor> doctors = Collections.singletonList(doctor);

    private PagedResult<Doctor> page = new PagedResult<>(doctors, 0, 2, 200);

    @Mock
    private DoctorDao doctorDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new DoctorServiceImpl();
        service.setDoctorDao(doctorDao);
    }

    @Test
    public void testGetAll() {
        when(doctorDao.getAll(anyInt())).thenReturn(page);

        assertEquals(page, service.getAll(0));
    }

    @Test
    public void testGet() {
        when(doctorDao.getById(anyInt())).thenReturn(doctor);

        assertEquals(doctor, service.get(2));
    }

    @Test
    public void testSearchByName() {
        when(doctorDao.searchByName(anyString(), anyString(), anyInt())).thenReturn(page);

        assertEquals(page, service.searchByName("", "", 0));
    }

    @Test
    public void testSearchBySpeciality() {
        when(doctorDao.getBySpeciality(eq(2), anyInt())).thenReturn(page);

        assertEquals(page, service.searchBySpeciality(2, 0));
    }

    @Test
    public void testFindByEmail() {
        when(doctorDao.getByEmail(eq("email"))).thenReturn(doctor);

        assertEquals(doctor, service.findByEmail("email"));
    }

    @Test
    public void testGetDoctorsByInstitution() {
        when(doctorDao.getDoctorsByInstitution(eq(5))).thenReturn(doctors);

        assertEquals(doctors, service.getDoctorsByInstitution(5));
    }

    @Test
    public void testHasNextPageForSearchByName() {
        when(doctorDao.hasNextPageForSearchByName(anyString(), anyString(), anyInt()))
                .thenReturn(true);

        assertTrue(service.hasNextPageForSearchByName("", "", 2));
    }
}
