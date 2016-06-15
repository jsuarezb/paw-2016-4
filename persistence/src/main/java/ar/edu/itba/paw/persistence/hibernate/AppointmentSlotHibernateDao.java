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
    public List<AppointmentSlot> getAvailableBySpecialityAndNeighborhood(Speciality speciality, String neighborhood,
                                                                         LocalDateTime weekStart) {

        final TypedQuery<AppointmentSlot> query = em.createQuery("select slot " +
                "from AppointmentSlot as slot JOIN slot.worksIn as worksIn JOIN worksIn.institution as institution " +
                "JOIN worksIn.doctor as doctor JOIN institution.address as address " +
                "JOIN doctor.specialities as speciality " +
                "where address.neighborhood = :neighborhood AND speciality.id = :speciality_id", AppointmentSlot.class);
        query.setParameter("neighborhood", neighborhood);
        query.setParameter("speciality_id", speciality.getId());
        return query.getResultList();
    }
}

