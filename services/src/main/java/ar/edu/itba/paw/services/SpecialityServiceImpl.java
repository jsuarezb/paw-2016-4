package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.SpecialityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityDao specialityDao;

    public List<Speciality> getAll() {
        return specialityDao.getAll();
    }

    public Speciality searchSpecialityByDescription(final String description) {
        return null;
    }

    public Speciality searchByName(final String name) {
        return specialityDao.getByName(name);
    }

    public Speciality getById(final Integer id) {
        return specialityDao.getById(id);
    }

    public Set<Speciality> getByInstitutionId(final Integer institution_id) {
        return specialityDao.getByInstitutionId(institution_id);
    }

    /* package */ void setSpecialityDao(SpecialityDao specialityDao) {
        this.specialityDao = specialityDao;
    }
}
