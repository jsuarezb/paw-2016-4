package ar.edu.itba.paw.services;


import ar.edu.itba.paw.persistence.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<String> getAllNeighborhoods() {
        return addressDao.getAllNeighborhoods();
    }

    /* package */ void setAddressDao(final AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
