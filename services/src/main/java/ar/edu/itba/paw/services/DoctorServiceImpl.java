package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by agophurmuz on 4/27/16.
 */
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    public Doctor get(Integer id) {
        return doctorDao.getById(id);
    }

    public Doctor searchByName(String name, String last_name) {
        return doctorDao.searchByName(name, last_name);
    }

    public List<Doctor> searchBySpeciality(Integer speciality_id) {
        return doctorDao.searchBySpeciality(speciality_id);
    }
}
