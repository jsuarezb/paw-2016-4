package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
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

    public List<AppointmentSlot> getAvailableBySpeciality(int speciality_id, LocalDateTime week) {
        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "FROM AppointmentSlot",
                AppointmentSlot.class
        );
        return query.getResultList();
    }

    @Override
    public List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(Speciality speciality, String neiborhood,
                                                                         LocalDateTime weekStart) {

        final Query query1 = em.createQuery("select i.id " +
                "FROM Institution as i where i.address IN (select a.id from Address as a where a.neighborhood = :neighborhood)");
        query1.setParameter("neighborhood", neiborhood);
        List<Integer> institutionsIds = query1.getResultList();

        final Query query2 = em.createQuery("select d.id from Doctor as d join d.specialities as s where s.speciality_id = :speciality_id");
        query2.setParameter("speciality_id", speciality.getId());
        List<Integer> doctorIds = query2.getResultList();

        final Query query3 = em.createQuery("select w.id from WorksIn as w " +
                "where w.doctor_id IN (:doctorIds) AND w.institution IN (:institutionsIds)");
        query3.setParameter("doctorIds", doctorIds);
        query3.setParameter("institutionsIds", institutionsIds);
        List<Integer> worksInIds = query3.getResultList();

        final TypedQuery<AppointmentSlot> query = em.createQuery(
                "FROM AppoinmentSlot as slot WHERE slot.worksIn IN (:worksInIds)", AppointmentSlot.class);
        query.setParameter("worksInIds", worksInIds);

        return query.getResultList();
    }
}

