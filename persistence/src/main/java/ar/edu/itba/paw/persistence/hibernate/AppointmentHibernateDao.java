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

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Appointment create(final Patient patient, final Doctor doctor,
                              final AppointmentSlot appointmentSlot, final LocalDateTime startDate,
                              final String comments) {
        final Appointment appointment = Appointment.builder()
                .setPatient(patient)
                .setSlot(appointmentSlot)
                .setDate(startDate)
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
    public List<Appointment> search(final Integer institution_id, final String neighborhood,
                                    final Integer speciality_id, final String firstName, final String lastName, final int page) {
        StringBuilder queryBuilder = new StringBuilder("SELECT a FROM Appointment AS a ")
                .append("WHERE (:institution_id IS NULL OR a.slot.worksIn.institution.id = :institution_id) ")
                .append("AND (:neighborhood IS NULL OR :neighborhood = '' OR a.slot.worksIn.institution.address.neighborhood = :neighborhood) ")
                .append("AND (:speciality_id = -1 OR :speciality_id = ANY (SELECT spec.id FROM a.slot.worksIn.doctor.specialities AS spec)) ")
                .append("AND (:first_name IS NULL OR :first_name = '' OR a.slot.worksIn.doctor.name = :first_name) ")
                .append("AND (:last_name IS NULL OR :last_name = '' OR a.slot.worksIn.doctor.lastName = :last_name) ")
                .append("ORDER BY a.date");

        final TypedQuery<Appointment> query = em.createQuery(queryBuilder.toString(), Appointment.class);
        query.setParameter("institution_id", institution_id);
        query.setParameter("neighborhood", neighborhood );
        query.setParameter("speciality_id", speciality_id == null ? -1 : speciality_id);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        query.setMaxResults(15);
        query.setFirstResult(page * 15);

        return query.getResultList();
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
    public boolean isDoctorAvailable(final Doctor doctor, final LocalDateTime date) {
        final TypedQuery<Integer> query = em.createQuery(
            "SELECT COUNT(*) " +
            "FROM Appointment AS app " +
              "JOIN app.slot.worksIn.doctor AS doctor " +
            "WHERE app.date = :start_date AND " +
              "doctor.id = :doctor_id", Integer.class);
        query.setParameter("doctor_id", doctor.getId());
        query.setParameter("start_date", date);
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
