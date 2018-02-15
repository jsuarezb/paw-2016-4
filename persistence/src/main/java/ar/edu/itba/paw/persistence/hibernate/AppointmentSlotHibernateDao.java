package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by agophurmuz on 5/18/16.
 */
@Repository
public class AppointmentSlotHibernateDao implements AppointmentSlotDao {

    private static final int PAGE_SIZE = 15;

    @PersistenceContext
    private EntityManager em;

    public AppointmentSlot create(final WorksIn worksIn,
                                  final int dayOfWeek,
                                  final int startHour) {
        final AppointmentSlot appointmentSlot = AppointmentSlot.builder()
                .setDayOfWeek(dayOfWeek)
                .setHour(startHour)
                .setWorksIn(worksIn)
                .build();

        em.persist(appointmentSlot);
        return appointmentSlot;
    }

    public AppointmentSlot getById(final int id) {
        return em.find(AppointmentSlot.class, id);
    }

    @Override
    public PagedResult<AppointmentSlot> search(final Integer weekNumber, final Integer year,
                                               final Integer institution_id, final Integer speciality_id,
                                               final String neighborhood, final Integer doctor_id,
                                               int page) {
        System.out.println(doctor_id);
        final StringBuilder baseQuery = new StringBuilder("FROM AppointmentSlot AS asl ")
                .append("WHERE NOT EXISTS (SELECT ap.id FROM asl.appointments ap WHERE ap.weekNumber = :week_number AND ap.year = :year) ") // TODO
                .append("AND (:institution_id IS NULL OR asl.worksIn.institution.id = :institution_id) ")
                .append("AND (:neighborhood IS NULL OR :neighborhood = '' OR asl.worksIn.institution.address.neighborhood = :neighborhood) ")
                .append("AND (:speciality_id = -1 OR :speciality_id = ANY (SELECT spec.id FROM asl.worksIn.doctor.specialities AS spec)) ")
                .append("AND (:doctor_id IS NULL OR asl.worksIn.doctor.id = :doctor_id) ");

        final StringBuilder rows = new StringBuilder("SELECT asl ").append(baseQuery).append("ORDER BY asl.dayOfWeek, asl.hour");
        final StringBuilder countRows = new StringBuilder("SELECT COUNT(asl) ").append(baseQuery);

        final TypedQuery<AppointmentSlot> query = em.createQuery(rows.toString(), AppointmentSlot.class);
        query.setParameter("institution_id", institution_id);
        query.setParameter("neighborhood", neighborhood );
        query.setParameter("speciality_id", speciality_id == null ? -1 : speciality_id);
        query.setParameter("doctor_id", doctor_id);
        query.setParameter("week_number", weekNumber);
        query.setParameter("year", year);

        query.setMaxResults(PAGE_SIZE);
        query.setFirstResult(page * PAGE_SIZE);

        final List<AppointmentSlot> appointments = query.getResultList();

        final TypedQuery<Long> countQuery = em.createQuery(countRows.toString(), Long.class);
        countQuery.setParameter("institution_id", institution_id);
        countQuery.setParameter("neighborhood", neighborhood );
        countQuery.setParameter("speciality_id", speciality_id == null ? -1 : speciality_id);
        countQuery.setParameter("doctor_id", doctor_id);
        countQuery.setParameter("week_number", weekNumber);
        countQuery.setParameter("year", year);

        final Long count = countQuery.getSingleResult();

        return new PagedResult<>(appointments, page, PAGE_SIZE, count);
    }

    public List<AppointmentSlot> getByDoctor(final Doctor doctor) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "FROM AppointmentSlot AS slot JOIN slot.worksIn as worksIn" +
                "WHERE worksIn.doctor_id = :doctor_id", AppointmentSlot.class);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor,
                                                      final Integer weekNumber,
                                                      final Integer year) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                "WHERE slot NOT IN " +
                    "(SELECT app.slot FROM Appointment AS app " +
                    "WHERE app.weekNumber = :week AND app.year = :year " +
                    "AND app.slot.worksIn.doctor.id = :doctor_id) " +
                "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        query.setParameter("week", weekNumber);
        query.setParameter("year", year);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableByDoctorInInstitution(final int doctorId,
                                                                   final int institutionId,
                                                                   final Integer weekNumber,
                                                                   final Integer year) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot FROM Appointment AS app " +
                        "WHERE app.weekNumber = :week AND app.year = :year " +
                        "AND app.slot.worksIn.doctor.id = :doctor_id " +
                        "AND app.slot.worksIn.institution.id = :institution_id) " +
                        "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        query.setParameter("week", weekNumber);
        query.setParameter("year", year);
        query.setParameter("institution_id", institutionId);
        query.setParameter("doctor_id", doctorId);
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableBySpecialityInInstitution(final int speciality_id,
                                                                       final int institution_id,
                                                                       final Integer weekNumber,
                                                                       final Integer year) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot " +
                        "FROM Appointment AS app " +
                        "JOIN app.slot.worksIn.doctor.specialities speciality " +
                        "WHERE app.weekNumber = :week AND app.year = :year " +
                        "AND speciality.id = :speciality_id " +
                        "AND app.slot.worksIn.institution.id = :institution_id) " +
                "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        query.setParameter("week", weekNumber);
        query.setParameter("year", year);
        query.setParameter("institution_id", institution_id);
        query.setParameter("speciality_id", speciality_id);
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableBySpeciality(final int speciality_id,
                                                          final Integer weekNumber,
                                                          final Integer year) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot " +
                        "FROM Appointment AS app " +
                        "JOIN app.slot.worksIn.doctor.specialities speciality " +
                        "WHERE app.weekNumber = :week AND app.year = :year " +
                        "AND speciality.id = :speciality_id) " +
                "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        query.setParameter("week", weekNumber);
        query.setParameter("year", year);
        return query.getResultList();
    }

    @Override
    public List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                                         final String neighborhood,
                                                                         final Integer weekNumber,
                                                                         final Integer year) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot " +
                        "FROM Appointment AS app " +
                        "JOIN app.slot.worksIn.doctor.specialities speciality " +
                        "JOIN app.slot.worksIn.institution.address address " +
                        "WHERE app.date > current_timestamp() " +
                        "AND app.weekNumber = :week " +
                        "AND app.year = :year " +
                        "AND speciality.id = :speciality_id " +
                        "AND address.neighborhood = :neighborhood) " +
                        "ORDER BY slot.dayOfWeek, slot.hour",
                AppointmentSlot.class);
        query.setParameter("neighborhood", neighborhood);
        query.setParameter("speciality_id", speciality.getId());
        query.setParameter("week", weekNumber);
        query.setParameter("year", year);
        return query.getResultList();
    }

    @Override
    public Doctor getDoctorInSlot(int slotId) {
        final TypedQuery<Doctor> query = em.createQuery(
                "SELECT doctor FROM AppointmentSlot AS slot JOIN slot.worksIn.doctor AS doctor " +
                        "WHERE slot.id = :slotId", Doctor.class);
        query.setParameter("slotId", slotId);
    return query.getSingleResult();
    }
}
