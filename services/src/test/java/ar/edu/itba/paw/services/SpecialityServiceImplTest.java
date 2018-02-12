package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.DoctorDao;
import ar.edu.itba.paw.persistence.SpecialityDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class SpecialityServiceImplTest {

    private SpecialityServiceImpl service;

    private Speciality speciality = new Speciality(1, "speciality");

    private List<Speciality> specialities = Collections.singletonList(speciality);

    private Set<Speciality> specialitySet = Sets.newSet(speciality);

    @Mock
    private SpecialityDao specialityDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new SpecialityServiceImpl();
        service.setSpecialityDao(specialityDao);
    }

    @Test
    public void testGetAll() {
        when(specialityDao.getAll()).thenReturn(specialities);

        assertEquals(specialities, service.getAll());
    }

    @Test
    public void testSearchByName() {
        final String name = "speciality";

        when(specialityDao.getByName(eq(name))).thenReturn(speciality);

        assertEquals(speciality, service.searchByName(name));
    }

    @Test
    public void testGetById() {
        final Integer id = 3;

        when(specialityDao.getById(eq(id))).thenReturn(speciality);

        assertEquals(speciality, service.getById(id));
    }

    @Test
    public void testGetByInstitutionId() {
        final Integer id = 6;

        when(specialityDao.getByInstitutionId(eq(id))).thenReturn(specialitySet);

        assertEquals(specialitySet, service.getByInstitutionId(id));
    }
}
