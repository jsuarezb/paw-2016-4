package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;

import java.util.List;

/**
 * Created by agophurmuz on 4/21/16.
 */
public interface DoctorService {

    List<Doctor> getAll();

    Doctor get(final Integer id);

    Doctor searchByName(final String name, final String last_name);

    List<Doctor> searchBySpeciality(final Integer speciality_id);

    /**
     * Get the doctors of an institution by its id
     * @param id Id of institution to search for
     * @return An array of doctors, null if there's no doctors
     *      for the specified id
     */
    List<Doctor> getDoctorsByInstitution(Integer id);

}
