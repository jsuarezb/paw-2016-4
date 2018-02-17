package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Rating;
import ar.edu.itba.paw.models.RatingSummary;
import ar.edu.itba.paw.persistence.RatingDao;
import ar.edu.itba.paw.services.exceptions.UpdateNonExistantRatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDao ratingDao;

    @Override
    public RatingSummary getDoctorSummary(final Doctor doctor) {
        Objects.requireNonNull(doctor);

        return ratingDao.getDoctorSummary(doctor);
    }

    @Override
    public Rating create(final Doctor doctor, final Patient patient, final Integer value)
        throws UpdateNonExistantRatingException {
        Objects.requireNonNull(doctor);
        Objects.requireNonNull(patient);
        Objects.requireNonNull(value);

        return ratingDao.create(doctor, patient, value);
    }

    @Override
    public Rating update(final Doctor doctor, final Patient patient, final Integer value) {
        Objects.requireNonNull(doctor);
        Objects.requireNonNull(patient);
        Objects.requireNonNull(value);

        final Boolean ratingExists = ratingDao.find(doctor, patient) != null;

        return ratingExists
                ? ratingDao.update(doctor, patient, value)
                : ratingDao.create(doctor, patient, value);
    }

    @Override
    public Rating get(final Doctor doctor, final Patient patient) {
        Objects.requireNonNull(doctor);
        Objects.requireNonNull(patient);

        return ratingDao.find(doctor, patient);
    }
}
