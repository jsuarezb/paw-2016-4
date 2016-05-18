package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Institution;

import java.util.List;

public interface InstitutionDao {

    /**
     * Get all institutions.
     *
     * @return A non null list with all the institutions.
     */
    List<Institution> getAll();

    /**
     * Get the user with the specified id.
     *
     * @param id User's id.
     * @return The user with the specified id if exists, else null
     */
    Institution getById(final Integer id);

    /**
     * Get all institutions which names matches the specified name.
     *
     * @param name Name to match with.
     * @return A non null list of institutions whose name matches the given name.
     */
    List<Institution> getByName(final String name);

}
