package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Institution;

import java.util.List;

public interface InstitutionService {

    /**
     * Shows all institutions
     * @return An array of institutions
     */
    List<Institution> getAll();

    /**
     * Get an institution by its id
     * @param id Id to search for
     * @return An institution, null if there's no institution
     *      with the specified id
     */
    Institution get(final int id);

    /**
     * Search an institution by its name
     * @param name Name to search for
     * @return An array of institutions
     */
    List<Institution> findByName(final String name);

}
