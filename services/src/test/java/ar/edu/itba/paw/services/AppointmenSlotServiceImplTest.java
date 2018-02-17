package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class AppointmenSlotServiceImplTest {

    private AppointmentSlotServiceImpl service;

    private Doctor doctor = new Doctor(1, "", "", Collections.emptySet(),
            Collections.emptySet(), "", "");

    private Institution institution = new Institution(
            "",
            new Address("", 3, "", "", "", "", "","",""),
            2
    );

    private WorksIn worksIn = new WorksIn(1, doctor, institution);

    private AppointmentSlot slot = new AppointmentSlot(1, 2, worksIn);

    private List<AppointmentSlot> slots = Collections.singletonList(slot);

    @Mock
    private AppointmentSlotDao slotDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new AppointmentSlotServiceImpl();
        service.setAppointmentSlotDao(slotDao);
    }

    @Test
    public void testCreate() {
        when(slotDao.create(any(WorksIn.class), anyInt(), anyInt())).thenReturn(slot);

        assertEquals(slot, service.create(worksIn, 2, 3));
    }

    @Test
    public void testGetById() {
        when(slotDao.getById(anyInt())).thenReturn(slot);

        assertEquals(slot, service.getById(1));
    }

    @Test
    public void testGetByDoctor() {
        when(slotDao.getByDoctor(eq(doctor))).thenReturn(slots);

        assertEquals(slots, service.getByDoctor(doctor));
    }

    @Test
    public void testGetAvailableByDoctor() {
        when(slotDao.getAvailableByDoctor(eq(doctor), anyInt(), anyInt())).thenReturn(slots);

        assertEquals(slots, service.getAvailableByDoctor(doctor, 2, 2017));
    }
}
