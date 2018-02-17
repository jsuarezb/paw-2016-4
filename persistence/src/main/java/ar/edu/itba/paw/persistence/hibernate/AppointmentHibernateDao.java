package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.persistence.AppointmentDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

@Repository
public class AppointmentHibernateDao implements AppointmentDao {

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
    // FIXME
    public List<Appointment> getAll() {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment", Appointment.class);
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
    public boolean isDoctorAvailable(final AppointmentSlot appointmentSlot, final Integer weekNumber, final Integer year) {
        final TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(*) " +
            "FROM Appointment AS app " +
              "JOIN app.slot AS slot " +
            "WHERE app.weekNumber = :week_number AND " +
                  "app.year = :year AND " +
              "slot.id = :slot_id", Long.class);
        query.setParameter("slot_id", appointmentSlot.getId());
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

    @Override
    public List<Appointment> getIncomingAppointments(final Doctor doctor, final LocalDateTime now) {
        final Integer year = now.getYear();
        final Integer weekNumber = now.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);

        final String query = "SELECT ap FROM Appointment AS ap " +
                "WHERE ap.slot.worksIn.doctor.id = :doctor_id " +
                "AND ap.weekNumber >= :week_number AND ap.year >= :year " +
                "ORDER BY ap.year ASC, ap.weekNumber ASC, ap.slot.dayOfWeek ASC, ap.slot.hour ASC";

        final TypedQuery<Appointment> typedQuery = em.createQuery(query, Appointment.class);
        typedQuery.setParameter("week_number", weekNumber);
        typedQuery.setParameter("year", year);
        typedQuery.setParameter("doctor_id", doctor.getId());

        return typedQuery.getResultList();
    }

    @Override
    public List<Appointment> getPastAppointments(Doctor doctor, LocalDateTime now) {
        final Integer year = now.getYear();
        final Integer weekNumber = now.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);

        final String query = "SELECT ap FROM Appointment AS ap " +
                "WHERE ap.slot.worksIn.doctor.id = :doctor_id " +
                "AND ap.year < :year OR (ap.year = :year AND ap.weekNumber < :week_number) " +
                "ORDER BY ap.year DESC, ap.weekNumber DESC, ap.slot.dayOfWeek DESC, ap.slot.hour DESC";

        final TypedQuery<Appointment> typedQuery = em.createQuery(query, Appointment.class);
        typedQuery.setParameter("week_number", weekNumber);
        typedQuery.setParameter("year", year);
        typedQuery.setParameter("doctor_id", doctor.getId());

        return typedQuery.getResultList();

    }
}
