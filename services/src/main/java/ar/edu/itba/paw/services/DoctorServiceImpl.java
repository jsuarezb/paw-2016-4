package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    public Doctor get(final Integer id) { return doctorDao.getById(id); }

    public List<Doctor> searchByName(final String name,
                                     final String lastName) {
        return doctorDao.searchByName(name, lastName);
    }

    public List<Doctor> searchBySpeciality(final Integer speciality_id) {
        return doctorDao.getBySpeciality(speciality_id);
    }

    @Override
    public Doctor findByEmail(String email) {
        return doctorDao.getByEmail(email);
    }

    public List<Doctor> getDoctorsByInstitution (final Integer id) {
        return doctorDao.getDoctorsByInstitution(id);
    }

    @Override
    public boolean hasNextPageForSearchByName(final String name,
                                              final String lastName,
                                              final Integer page) {
        return doctorDao.hasNextPageForSearchByName(name, lastName, page);
    }

    /* package */ void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }
}
