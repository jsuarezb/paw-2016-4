package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by agophurmuz on 4/27/16.
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    public Doctor get(Integer id) { return doctorDao.getById(id);
    }

    public Doctor searchByName(String name, String last_name) {
        // TODO return doctorDao.getByName(name, last_name);
        return null;
    }

    public List<Doctor> searchBySpeciality(Integer speciality_id) { return doctorDao.getBySpeciality(speciality_id);
    }

    public List<Doctor> getDoctorsByInstitution (Integer id) { return doctorDao.getDoctorsByInstitution(id); }
}
