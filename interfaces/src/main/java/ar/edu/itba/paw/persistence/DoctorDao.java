package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;

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

    List<Doctor> searchByName(String name, String lastName, Integer page);

    boolean hasNextPageForSearchByName(String name, String lastName, Integer page);
}
