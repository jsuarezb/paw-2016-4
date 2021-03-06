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

    @Autowired
    private MailService mailService;

    public List<Patient> getAll() {
        return patientDao.getAll();
    }

    public Patient get(final int id) {
        return patientDao.getById(id);
    }

    public boolean login(final String email, final String password) {
        final Patient patient = findByEmail(email);
        if (patient != null) {
            return password.equals(patient.getUser().getPassword());
        }
        return false;
    }

    public Patient findByEmail(final String email) {
        return patientDao.getByEmail(email);
    }

    /* default */ void setPatientDao(final PatientDao patientDao) {
        this.patientDao = patientDao;
    }
}
