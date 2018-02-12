package ar.edu.itba.paw.services;

import ar.edu.itba.paw.persistence.AddressDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

public class AddresServiceImplTest {

    private AddressServiceImpl service;

    @Mock
    private AddressDao dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(dao.getAllNeighborhoods()).thenReturn(Arrays.asList("Address 1", "Address 2"));

        service = new AddressServiceImpl();
        service.setAddressDao(dao);
    }

    @Test
    public void testGetAllNeighborhoods() {
        List<String> neighborhoods = service.getAllNeighborhoods();

        assertArrayEquals(Arrays.asList("Address 1", "Address 2").toArray(), neighborhoods.toArray());
    }
}
