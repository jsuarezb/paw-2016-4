package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppointmentHibernateDao implements AppointmentDao {

    private static final int PAGE_SIZE = 15;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Appointment create(final Patient patient, final Doctor doctor,
                              final AppointmentSlot appointmentSlot,
                              final Integer weekNumber, final Integer year,
                              final String comments) {
        final Appointment appointment = Appointment.builder()
                .setPatient(patient)
                .setSlot(appointmentSlot)
                .setWeek(weekNumber, year)
                .setComments(comments)
                .build();

        em.persist(appointment);
        return appointment;
    }
    //FIXME
    public List<Appointment> getAll() {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment", Appointment.class);
        return query.getResultList();
    }

    @Transactional
    public PagedResult<Appointment> search(final Integer institution_id, final String neighborhood,
                                    final Integer speciality_id, final String firstName, final String lastName, final int page) {
        StringBuilder baseQuery = new StringBuilder("FROM Appointment AS a ")
                .append("WHERE (:institution_id IS NULL OR a.slot.worksIn.institution.id = :institution_id) ")
                .append("AND (:neighborhood IS NULL OR :neighborhood = '' OR a.slot.worksIn.institution.address.neighborhood = :neighborhood) ")
                .append("AND (:speciality_id = -1 OR :speciality_id = ANY (SELECT spec.id FROM a.slot.worksIn.doctor.specialities AS spec)) ")
                .append("AND (:first_name IS NULL OR :first_name = '' OR a.slot.worksIn.doctor.name = :first_name) ")
                .append("AND (:last_name IS NULL OR :last_name = '' OR a.slot.worksIn.doctor.lastName = :last_name) ");

        final StringBuilder rows = new StringBuilder("SELECT a ").append(baseQuery).append("ORDER BY a.date");
        final StringBuilder countRows = new StringBuilder("SELECT COUNT(a) ").append(baseQuery);

        final TypedQuery<Appointment> query = em.createQuery(rows.toString(), Appointment.class);
        query.setParameter("institution_id", institution_id);
        query.setParameter("neighborhood", neighborhood );
        query.setParameter("speciality_id", speciality_id == null ? -1 : speciality_id);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        query.setMaxResults(PAGE_SIZE);
        query.setFirstResult(page * PAGE_SIZE);

        final List<Appointment> appointments = query.getResultList();

        final TypedQuery<Long> countQuery = em.createQuery(countRows.toString(), Long.class);
        countQuery.setParameter("institution_id", institution_id);
        countQuery.setParameter("neighborhood", neighborhood );
        countQuery.setParameter("speciality_id", speciality_id == null ? -1 : speciality_id);
        countQuery.setParameter("first_name", firstName);
        countQuery.setParameter("last_name", lastName);

        final Long count = countQuery.getSingleResult();

        return new PagedResult<Appointment>(appointments, page, PAGE_SIZE, count);
    }

    @Transactional
    public List<Appointment> getByDoctor(final Doctor doctor) {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment AS a JOIN AppointmentSlot AS asl " +
                "ON a.appointment_slot_id = asl.id WHERE asl.doctor_id = :doctor_id", Appointment.class);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();

    }
    @Transactional
    public List<Appointment> getByPatient(final Patient patient, final int page) {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment AS a " +
                "WHERE a.patient.id = :patient_id", Appointment.class);
        query.setParameter("patient_id", patient.getId());
        return query.getResultList();
    }
    @Transactional
    public boolean isDoctorAvailable(final Doctor doctor, final Integer weekNumber, final Integer year) {
        final TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(*) " +
            "FROM Appointment AS app " +
              "JOIN app.slot.worksIn.doctor AS doctor " +
            "WHERE app.weekNumber = :week_number AND " +
                  "app.year = :year AND " +
              "doctor.id = :doctor_id", Long.class);
        query.setParameter("doctor_id", doctor.getId());
        query.setParameter("week_number", weekNumber);
        query.setParameter("year", year);
        return query.getSingleResult() == 0;
    }

    @Transactional
    public boolean delete(final int appointmentId) {
        final Query query = em.createQuery("DELETE FROM Appointment WHERE id = :id");
        query.setParameter("id", appointmentId);
        return query.executeUpdate() == 1;
    }

    @Override
    public Appointment getByid(final int appointmentId) {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment AS a WHERE a.id = :id", Appointment.class);
        query.setParameter("id", appointmentId);
        return query.getSingleResult();
    }
}
