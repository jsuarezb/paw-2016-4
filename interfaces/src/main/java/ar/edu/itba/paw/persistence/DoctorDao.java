package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
public interface DoctorDao {

    List<Doctor> getAll();

    Doctor searchByName(final String name, final String last_name);

    Doctor getById(final Integer id);

    List<Doctor> searchBySpeciality(final Integer speciality_id);

    /**
     * Get all doctors of an institution.
     *
     * * @param id Institution id.
     * @return A list with the doctors of an institution.
     */
    List<Doctor> getDoctorsByInstitution(Integer id);
}
