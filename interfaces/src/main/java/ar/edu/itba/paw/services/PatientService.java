package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Patient;

import java.util.List;

/**
 * Created by santi698 on 20/04/16.
 */
public interface PatientService {
    List<Patient> getAll();
    Patient get(int id);
    Patient findByEmail(final String email);

    boolean login(final String email, final String password);
}
