package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAll();

    Doctor get(final Integer id);

    List<Doctor> searchByName(final String name, final String lastName, Integer page);

    List<Doctor> searchBySpeciality(final Integer speciality_id);

    /**
     * Get the doctors of an institution by its id
     * @param id Id of institution to search for
     * @return An array of doctors, null if there's no doctors
     *      for the specified id
     */
    List<Doctor> getDoctorsByInstitution(Integer institution_id);

    boolean hasNextPageForSearchByName(String name, String lastName, Integer page);
}
