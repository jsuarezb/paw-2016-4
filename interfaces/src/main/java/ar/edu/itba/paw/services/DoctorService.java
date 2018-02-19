package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.PagedResult;

import java.util.List;

public interface DoctorService {

    PagedResult<Doctor> getAll(Integer page);

    Doctor get(Integer id);

    PagedResult<Doctor> searchByName(String name, String lastName, Integer page);

    PagedResult<Doctor> searchBySpeciality(Integer speciality_id, Integer pageSize);

    PagedResult<Doctor> getAvailable(Integer specialityId, String neighborhood, Integer institutionId, Integer weekOfYear,
                                     Integer year, int page);

    Doctor findByEmail(String email);

    /**
     * Get the doctors of an institution by its id
     *
     * @param id Id of institution to search for
     * @return An array of doctors, null if there's no doctors
     * for the specified id
     */
    List<Doctor> getDoctorsByInstitution(Integer institution_id);

    boolean hasNextPageForSearchByName(String name,
                                       String lastName,
                                       Integer page);
}
