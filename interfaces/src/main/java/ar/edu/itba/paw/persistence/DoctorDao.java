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
    List<Doctor> getDoctorsByInstitution(final Integer id);

    Doctor getByName(final String name, final String last_name);

    List<Doctor> searchByName(final String name,
                              final String lastName,
                              final Integer page);

    boolean hasNextPageForSearchByName(final String name,
                                       final String lastName,
                                       final Integer page);
}
