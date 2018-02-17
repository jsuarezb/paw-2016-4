package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Rating;
import ar.edu.itba.paw.models.RatingSummary;

public interface RatingDao {

    Rating create(Doctor doctor, Patient patient, Integer value);

    Rating update(Doctor doctor, Patient patient, Integer value);

    Rating find(Doctor doctor, Patient patient);

    RatingSummary getDoctorSummary(Doctor doctor);

}
