package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
public interface DoctorDao {

    List<Doctor> getAll();

    Doctor getById(final Integer id);

    List<Doctor> getBySpeciality(final Integer speciality_id);

    /**
     * Get all doctors of an institution.
     *
     * * @param id Institution id.
     * @return A list with the doctors of an institution.
     */
    List<Doctor> getDoctorsByInstitution(Integer id);

    Doctor getByName(String name, String last_name);
}
