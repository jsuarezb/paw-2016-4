package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.SpecialityDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityDao specialityDao;

    public List<Speciality> getAll() {
        return specialityDao.getAll();
    }

    public Speciality searchSpecialityByDescription(String description) {
        return null;
    }
}
