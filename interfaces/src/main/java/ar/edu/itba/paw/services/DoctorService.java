package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAll();

    Doctor get(final Integer id);

    List<Doctor> searchByName(final String name, final String lastName);

    List<Doctor> searchBySpeciality(final Integer speciality_id);

    Doctor findByEmail(final String email);

    /**
     * Get the doctors of an institution by its id
     *
     * @param id Id of institution to search for
     * @return An array of doctors, null if there's no doctors
     * for the specified id
     */
    List<Doctor> getDoctorsByInstitution(final Integer institution_id);

    boolean hasNextPageForSearchByName(final String name,
                                       final String lastName,
                                       final Integer page);
}
