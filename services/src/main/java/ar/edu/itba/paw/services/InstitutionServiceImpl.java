package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.persistence.InstitutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionDao institutionDao;

    public List<Institution> getAll() {
        return institutionDao.getAll();
    }

    public Institution get(final int id) {
        return institutionDao.getById(id);
    }

    public List<Institution> findByName(final String name) {
        return institutionDao.getByName(name);
    }

    /* default */ void setInstitutionDao(final InstitutionDao institutionDao) {
        this.institutionDao = institutionDao;
    }
}
