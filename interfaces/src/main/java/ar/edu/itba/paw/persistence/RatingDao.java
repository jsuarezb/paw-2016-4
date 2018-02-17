package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Rating;
import ar.edu.itba.paw.models.RatingSummary;

public interface RatingDao {

    Rating create(final Doctor doctor, final Patient patient, final Integer value);

    Rating update(final Doctor doctor, final Patient patient, final Integer value);

    Rating find(final Doctor doctor, final Patient patient);

    RatingSummary getDoctorSummary(final Doctor doctor);

}
