package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Patient;

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
     * Get the patient with the specified email.
     *
     * @param email Email to match with.
     * @return A patient. Null if there's no patient with that email.
     */
    Patient getByEmail(final String email);

}
