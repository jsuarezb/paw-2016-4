package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.persistence.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    public List<Patient> getAll() {
        return patientDao.getAll();
    }

    public Patient get(final int id) {
        return patientDao.getById(id);
    }

    public Patient create(final String name,
                          final String last_name,
                          final String email,
                          final String password) {
        return patientDao.create(name, last_name, email, password);
    }

    public boolean login(final String email, final String password) {
        Patient patient = findByEmail(email);
        return patient != null || password.equals(patient.getPassword());
    }

    public Patient findByEmail(final String email) {
        return patientDao.getByEmail(email);
    }

    /* default */ void setPatientDao(final PatientDao patientDao) {
        this.patientDao = patientDao;
    }
}
