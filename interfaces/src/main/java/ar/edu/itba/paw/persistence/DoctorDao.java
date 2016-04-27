package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.Doctor;

import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
public interface DoctorDao {

    List<Doctor> getAll();

    Doctor searchByName(final String name, final String last_name);

    Doctor getById(final Integer id);

    List<Doctor> searchBySpeciality(final Integer speciality_id);
}
