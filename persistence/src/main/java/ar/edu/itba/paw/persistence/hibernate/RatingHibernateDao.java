package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.Rating;
import ar.edu.itba.paw.models.RatingSummary;
import ar.edu.itba.paw.persistence.RatingDao;
import ar.edu.itba.paw.services.exceptions.RatingAlreadyExistsException;
import ar.edu.itba.paw.persistence.exceptions.UpdateNoRatingException;
import ar.edu.itba.paw.services.exceptions.UpdateNonExistantRatingException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RatingHibernateDao implements RatingDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Rating create(final Doctor doctor, final Patient patient, final Integer value) {
        final Rating rating = new Rating(doctor, patient, value);
        em.persist(rating);

        return rating;
    }

    @Override
    public Rating update(Doctor doctor, Patient patient, Integer value)
            throws UpdateNonExistantRatingException, UpdateNoRatingException {
        final Query query = em.createQuery("UPDATE Rating r SET value = :value");
        query.setParameter("value", value);

        int result = query.executeUpdate();
        if (result == 0)
            throw new UpdateNoRatingException();

        return new Rating(doctor, patient, value);
    }

    @Override
    public Rating find(final Doctor doctor, final Patient patient) {
        final TypedQuery<Rating> query =
                em.createQuery("FROM Rating r " +
                        "WHERE r.doctor.id = :doctor_id AND r.patient.id = :patient_id", Rating.class);

        query.setParameter("doctor_id", doctor.getId());
        query.setParameter("patient_id", patient.getId());

        return query.getSingleResult();
    }

    @Override
    public RatingSummary getDoctorSummary(Doctor doctor) {
        final TypedQuery<Double> averageQuery = em.createQuery("SELECT AVG(r.value) " +
                "FROM Rating r " +
                "WHERE r.doctor.id = :doctor_id", Double.class);

        averageQuery.setParameter("doctor_id", doctor.getId());

        final Double averageRating = averageQuery.getSingleResult();

        final Map<Integer, Long> valueCount = new HashMap<>();

        RatingSummary.RATING_VALUES
                .forEach(value -> {
                    final TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(r) " +
                            "FROM Rating r " +
                            "WHERE r.doctor.id = :doctor_id", Long.class);

                    countQuery.setParameter("doctor_id", doctor.getId());

                    final Long count = countQuery.getSingleResult();

                    valueCount.put(value, count);
                });

        return new RatingSummary(averageRating, valueCount);
    }
}
