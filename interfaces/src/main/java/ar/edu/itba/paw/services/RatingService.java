package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Rating;
import ar.edu.itba.paw.models.RatingSummary;

public interface RatingService {

    /**
     * Returns the summary of ratings for a doctor
     * @param doctor
     * @return summary of ratings
     */
    RatingSummary getDoctorSummary(final Doctor doctor);

    /**
     * Creates a new rating
     * @param doctor
     * @param patient
     * @param value
     * @return the created rating
     */
    Rating create(final Doctor doctor, final Patient patient, final Integer value);

    /**
     * Updates a rating with a new value
     * @param doctor
     * @param patient
     * @param value
     * @return the updated rating
     */
    Rating update(final Doctor doctor, final Patient patient, final Integer value);

    /**
     * Returns the rating set by the patient
     * @param doctor
     * @param patient
     * @return the rating if it exists, else null
     */
    Rating get(final Doctor doctor, final Patient patient);

}
