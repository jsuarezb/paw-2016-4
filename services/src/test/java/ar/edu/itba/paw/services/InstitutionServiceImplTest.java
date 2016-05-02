package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.persistence.InstitutionDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class InstitutionServiceImplTest {

    private static final String SEARCH_NAME = "Clinica del Sol";
    private static final int ID = 1;

    private InstitutionServiceImpl service;

    @Mock
    private InstitutionDao dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Address address1 = new Address("Av. Coronel Diaz", 2211, null, "CABA", "Bs As", "Argentina");
        Institution institution1 = new Institution(1, "Clinica del Sol", address1);
        Address address2 = new Address("Cerviño", 3356, null, "CABA", "Bs As", "Argentina");
        Institution institution2 = new Institution(2, "Hospital Fernandez", address2);

        List<Institution> allInstitutions = new ArrayList<Institution>();
        allInstitutions.add(institution1);
        allInstitutions.add(institution2);
        when(dao.getAll()).thenReturn(allInstitutions);

        List<Institution> searchList = new ArrayList<Institution>();
        searchList.add(institution1);
        when(dao.searchByName(SEARCH_NAME)).thenReturn(searchList);

        when(dao.getById(ID)).thenReturn(institution1);

        service = new InstitutionServiceImpl();
        service.setInstitutionDao(dao);
    }

    @Test
    public void testGetAll() {
        List<Institution> list = service.getAll();

        assertNotNull(list);
    }

    @Test
    public void testGetById() {
        Institution institution = service.get(ID);

        assertNotNull(institution);
        assertEquals(ID, institution.getId());
        assertEquals("Clinica del Sol", institution.getName());
    }

    @Test
    public void testSearchByName() {
        List<Institution> list = service.findByName(SEARCH_NAME);

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(SEARCH_NAME, list.get(0).getName());
    }

}
