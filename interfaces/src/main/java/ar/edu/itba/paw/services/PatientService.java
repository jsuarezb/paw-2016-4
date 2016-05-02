package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Patient;

import java.util.List;

/**
 * Created by santi698 on 20/04/16.
 */
public interface PatientService {
    List<Patient> getAll();
    Patient get(int id);
    Patient create(String name,
                   String last_name,
                   String email,
                   String password);
    Patient findByEmail(String email);

    boolean login(String email, String password);
}
