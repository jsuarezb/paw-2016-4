package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.PatientPhone;

import java.util.List;

public interface PatientPhoneDao {

    List<PatientPhone> getByPatientId(final int id);

}
