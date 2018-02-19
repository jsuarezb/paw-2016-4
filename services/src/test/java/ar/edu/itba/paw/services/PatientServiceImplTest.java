package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.persistence.PatientDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class PatientServiceImplTest {

    private PatientServiceImpl service;

    private Patient patient = new Patient();

    private List<Patient> patients = Collections.singletonList(patient);

    @Mock
    private PatientDao patientDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new PatientServiceImpl();
        service.setPatientDao(patientDao);
    }

    @Test
    public void testGetAll() {
        when(patientDao.getAll()).thenReturn(patients);

        assertEquals(patients, service.getAll());
    }

    @Test
    public void testGet() {
        when(patientDao.getById(eq(3))).thenReturn(patient);

        assertEquals(patient, service.get(3));
    }

    @Test
    public void testFindByEmail() {
        when(patientDao.getByEmail(eq("e@mail.com"))).thenReturn(patient);

        assertEquals(patient, service.findByEmail("e@mail.com"));
    }
}
