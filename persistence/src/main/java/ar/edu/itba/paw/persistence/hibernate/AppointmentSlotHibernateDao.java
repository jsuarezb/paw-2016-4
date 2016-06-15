package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

/**
 * Created by agophurmuz on 5/18/16.
 */
@Repository
public class AppointmentSlotHibernateDao implements AppointmentSlotDao {

    @PersistenceContext
    private EntityManager em;


    public AppointmentSlot create(WorksIn worksIn, int dayOfWeek, int startHour) {
        AppointmentSlot appointmentSlot = new AppointmentSlot(dayOfWeek, startHour, worksIn);
        em.persist(appointmentSlot);
        return appointmentSlot;
    }

    public AppointmentSlot getById(int id) {
        return em.find(AppointmentSlot.class, id);
    }

    public List<AppointmentSlot> getByDoctor(Doctor doctor) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "FROM AppointmentSlot AS slot JOIN slot.worksIn as worksIn" +
                "WHERE worksIn.doctor_id = :doctor_id", AppointmentSlot.class);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();
    }

    public List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, LocalDateTime startDate) {
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

    public List<AppointmentSlot> getAvailableByDoctorInInstitution(int doctorId, int institutionId,
                                                                   LocalDateTime startDate) {
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

    public List<AppointmentSlot> getAvailableBySpecialityInInstitution(int speciality_id,
                                                                       int institution_id,
                                                                       LocalDateTime startDate) {
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

    public List<AppointmentSlot> getAvailableBySpeciality(int speciality_id, LocalDateTime startDate) {
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
    public List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(Speciality speciality, String neighborhood,
                                                                         LocalDateTime weekStart) {

        final TypedQuery<AppointmentSlot> query = em.createQuery("select slot " +
                "from AppointmentSlot as slot " +
                    "JOIN slot.worksIn as worksIn " +
                    "JOIN worksIn.institution as institution " +
                    "JOIN worksIn.doctor as doctor " +
                    "JOIN institution.address as address " +
                    "JOIN doctor.specialities as speciality " +
                "where address.neighborhood = :neighborhood " +
                    "AND speciality.id = :speciality_id", AppointmentSlot.class);
        query.setParameter("neighborhood", neighborhood);
        query.setParameter("speciality_id", speciality.getId());
        return query.getResultList();
    }
}
