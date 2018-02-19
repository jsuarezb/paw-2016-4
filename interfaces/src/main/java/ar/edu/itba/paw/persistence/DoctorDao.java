package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.PagedResult;

import java.util.List;

public interface DoctorDao {

    PagedResult<Doctor> getAll(Integer page);

    Doctor getById(Integer id);

    PagedResult<Doctor> getBySpeciality(Integer speciality_id, Integer pageSize);

    /**
     * Get all doctors of an institution.
     * <p>
     * * @param id Institution id.
     *
     * @return A list with the doctors of an institution.
     */
    List<Doctor> getDoctorsByInstitution(Integer id);

    Doctor getByEmail(String email);

    Doctor getByName(String name, String last_name);

    PagedResult<Doctor> searchByName(String name, String lastName, Integer page);

    PagedResult<Doctor> listAvailable(Integer specialityId, String neighborhood, Integer institutionId, Integer weekOfYear,
                                      Integer year, int page);

    boolean hasNextPageForSearchByName(String name, String lastName, Integer page);
}
