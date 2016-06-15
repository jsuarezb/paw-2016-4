package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    public Doctor get(Integer id) { return doctorDao.getById(id); }

    public List<Doctor> searchByName(String name, String lastName, Integer page) {
        if (page < 0)
            return Collections.emptyList();

        return doctorDao.searchByName(name, lastName, page);
    }

    public List<Doctor> searchBySpeciality(Integer speciality_id) { return doctorDao.getBySpeciality(speciality_id);
    }

    public List<Doctor> getDoctorsByInstitution (Integer id) { return doctorDao.getDoctorsByInstitution(id); }

    @Override
    public boolean hasNextPageForSearchByName(String name, String lastName, Integer page) {
        return doctorDao.hasNextPageForSearchByName(name, lastName, page);
    }
}
