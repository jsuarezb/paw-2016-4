package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.Patient;

import java.util.List;

/**
 * Created by santi698 on 20/04/16.
 */
public interface PatientDao {
    /**
     * Get all patients.
     *
     * @return A non null list with all the patients.
     */
    List<Patient> getAll();

    /**
     * Get the patient with the specified id.
     *
     * @param id Patient's id.
     * @return The patient with the specified id if exists, else null
     */
    Patient getById(final Integer id);

    /**
     * Get all patients whose emails matches the specified email.
     *
     * @param email Email to match with.
     * @return A non null list of institutions whose email matches the given email.
     */
    Patient findByEmail(final String email);

    Patient create(String name, String last_name, String email, String password);
}
