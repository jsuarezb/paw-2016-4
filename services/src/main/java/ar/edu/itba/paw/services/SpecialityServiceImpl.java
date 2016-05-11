package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.DoctorDao;
import ar.edu.itba.paw.persistence.SpecialityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by agophurmuz on 4/22/16.
 */
@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityDao specialityDao;

    @Autowired
    private DoctorDao doctorDao;

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

    public Set<Speciality> getByInstitutionId(Integer institution_id) {
        List<Doctor> doctors = doctorDao.getDoctorsByInstitution(institution_id);

        Set<Speciality> specialities = new HashSet<Speciality>();
        for ( Doctor doctor : doctors) {
            for (Speciality speciality: doctor.getSpecialities())
                specialities.add(speciality);
        }
        return specialities;
    }
}
