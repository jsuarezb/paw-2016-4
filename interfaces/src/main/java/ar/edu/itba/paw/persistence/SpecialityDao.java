package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Speciality;

import java.util.List;
import java.util.Set;

/**
 * Created by agophurmuz on 4/22/16.
 */
public interface SpecialityDao {

    List<Speciality> getAll();

    Speciality getByName(final String name);

    Speciality getById(final Integer id);

    List<Speciality> getByDoctorId(final Integer doctor_id);

    List<Speciality> getByInstitutionId(Integer institution_id);
}
