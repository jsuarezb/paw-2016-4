package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.WorksIn;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<AppointmentSlot> getAvailableByDoctor(Doctor doctor, LocalDateTime week) {
        return Collections.emptyList();
    }

    public List<AppointmentSlot> getAvailableByDoctorInInstitution(int doctorId, int institutionId, LocalDateTime weekStart) {
        return Collections.emptyList();
    }

    public List<AppointmentSlot> getAvailableBySpecialityInInstitution(int speciality_id, int institution_id, LocalDateTime week) {
        return Collections.emptyList();
    }

    public List<AppointmentSlot> getAvailableBySpeciality(int speciality_id, LocalDateTime startDate) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "SELECT slot " +
                "FROM Appointment AS app " +
                "RIGHT JOIN app.slot AS slot " +
                "WHERE ((app.date > :start_date AND app.date < :end_date) " +
                        "OR app.date IS NULL) " +
                "AND app.slot IS NULL"
                ,
                AppointmentSlot.class
        );
        LocalDateTime endDate = startDate.plus(7, ChronoUnit.DAYS);
        query.setParameter("start_date", startDate);
        query.setParameter("end_date", endDate);
        return query.getResultList();
    }
}
