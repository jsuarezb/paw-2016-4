package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by agophurmuz on 5/18/16.
 */
@Repository
public class AppointmentSlotHibernateDao implements AppointmentSlotDao {

    @PersistenceContext
    private EntityManager em;


    public AppointmentSlot create(final WorksIn worksIn,
                                  final int dayOfWeek,
                                  final int startHour) {
        AppointmentSlot appointmentSlot = new AppointmentSlot(dayOfWeek, startHour, worksIn);
        em.persist(appointmentSlot);
        return appointmentSlot;
    }

    public AppointmentSlot getById(final int id) {
        return em.find(AppointmentSlot.class, id);
    }

    public List<AppointmentSlot> getByDoctor(final Doctor doctor) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "FROM AppointmentSlot AS slot JOIN slot.worksIn as worksIn" +
                "WHERE worksIn.doctor_id = :doctor_id", AppointmentSlot.class);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableByDoctor(final Doctor doctor,
                                                      final LocalDateTime startDate) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                "WHERE slot NOT IN " +
                    "(SELECT app.slot FROM Appointment AS app " +
                    "WHERE app.date > :start_date AND app.date < :end_date " +
                    "AND app.slot.worksIn.doctor.id = :doctor_id) " +
                "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        LocalDateTime endDate = startDate.plus(7, ChronoUnit.DAYS);
        query.setParameter("start_date", startDate);
        query.setParameter("end_date", endDate);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableByDoctorInInstitution(final int doctorId,
                                                                   final int institutionId,
                                                                   final LocalDateTime startDate) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot FROM Appointment AS app " +
                        "WHERE app.date > :start_date AND app.date < :end_date " +
                        "AND app.slot.worksIn.doctor.id = :doctor_id " +
                        "AND app.slot.worksIn.institution.id = :institution_id) " +
                        "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        LocalDateTime endDate = startDate.plus(7, ChronoUnit.DAYS);
        query.setParameter("start_date", startDate);
        query.setParameter("end_date", endDate);
        query.setParameter("institution_id", institutionId);
        query.setParameter("doctor_id", doctorId);
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableBySpecialityInInstitution(final int speciality_id,
                                                                       final int institution_id,
                                                                       final LocalDateTime startDate) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot " +
                        "FROM Appointment AS app " +
                        "JOIN app.slot.worksIn.doctor.specialities speciality " +
                        "WHERE app.date > :start_date AND app.date < :end_date " +
                        "AND speciality.id = :speciality_id " +
                        "AND app.slot.worksIn.institution.id = :institution_id) " +
                "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        LocalDateTime endDate = startDate.plus(7, ChronoUnit.DAYS);
        query.setParameter("start_date", startDate);
        query.setParameter("end_date", endDate);
        query.setParameter("institution_id", institution_id);
        query.setParameter("speciality_id", speciality_id);
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableBySpeciality(final int speciality_id,
                                                          final LocalDateTime startDate) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot " +
                        "FROM Appointment AS app " +
                        "JOIN app.slot.worksIn.doctor.specialities speciality " +
                        "WHERE app.date > :start_date AND app.date < :end_date " +
                        "AND speciality.id = :speciality_id) " +
                "ORDER BY slot.dayOfWeek, slot.hour"
                ,
                AppointmentSlot.class
        );
        LocalDateTime endDate = startDate.plus(7, ChronoUnit.DAYS);
        query.setParameter("start_date", startDate);
        query.setParameter("end_date", endDate);
        return query.getResultList();
    }

    @Override
    public List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(final Speciality speciality,
                                                                         final String neighborhood,
                                                                         final LocalDateTime weekStart) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot FROM AppointmentSlot AS slot " +
                        "WHERE slot NOT IN " +
                        "(SELECT app.slot " +
                        "FROM Appointment AS app " +
                        "JOIN app.slot.worksIn.doctor.specialities speciality " +
                        "JOIN app.slot.worksIn.institution.address address " +
                        "WHERE app.date > current_timestamp() " +
                        "AND app.date > :start_date " +
                        "AND app.date < :end_date " +
                        "AND speciality.id = :speciality_id " +
                        "AND address.neighborhood = :neighborhood) " +
                        "ORDER BY slot.dayOfWeek, slot.hour",
                AppointmentSlot.class);
        query.setParameter("neighborhood", neighborhood);
        query.setParameter("speciality_id", speciality.getId());
        LocalDateTime endDate = weekStart.plus(7, ChronoUnit.DAYS);
        query.setParameter("start_date", weekStart);
        query.setParameter("end_date", endDate);
        return query.getResultList();
    }
}
