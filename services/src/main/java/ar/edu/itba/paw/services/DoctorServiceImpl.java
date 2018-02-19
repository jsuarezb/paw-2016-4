package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.PagedResult;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public PagedResult<Doctor> getAll(final Integer page) {
        return doctorDao.getAll(page);
    }

    public Doctor get(final Integer id) { return doctorDao.getById(id); }

    public PagedResult<Doctor> searchByName(final String name, final String lastName, final Integer page) {
        return doctorDao.searchByName(name, lastName, page);
    }

    public PagedResult<Doctor> searchBySpeciality(final Integer speciality_id, final Integer page) {
        return doctorDao.getBySpeciality(speciality_id, page);
    }

    @Override
    public PagedResult<Doctor> getAvailable(Integer specialityId, String neighborhood, Integer institutionId, Integer weekOfYear, Integer year, int page) {
        return doctorDao.listAvailable(specialityId, neighborhood, institutionId, weekOfYear, year, page);
    }

    @Override
    public Doctor findByEmail(final String email) {
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

    /* package */ void setDoctorDao(final DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }
}
