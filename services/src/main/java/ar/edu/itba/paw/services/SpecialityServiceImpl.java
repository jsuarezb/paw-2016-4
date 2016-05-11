package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.SpecialityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityDao specialityDao;

    public List<Speciality> getAll() {
        return specialityDao.getAll();
    }

    public Speciality searchSpecialityByDescription(String description) {
        return null;
    }

    public Speciality searchByName(String name) {
        return specialityDao.searchByName(name);
    }

    public Speciality getById(Integer id) {
        return specialityDao.getById(id);
    }


}