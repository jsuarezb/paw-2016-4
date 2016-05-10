package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Speciality;

import java.util.List;

/**
 * Created by agophurmuz on 4/21/16.
 */
public interface SpecialityService {

    List<Speciality> getAll();

    Speciality searchSpecialityByDescription(final String description);

    Speciality searchByName(final String name);

    Speciality getById(final Integer id);
}
