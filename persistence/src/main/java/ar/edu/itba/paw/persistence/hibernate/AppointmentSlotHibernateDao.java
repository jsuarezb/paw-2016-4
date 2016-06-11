package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.persistence.AppointmentSlotDao;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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


    public AppointmentSlot create(Institution institution, Doctor doctor, int dayOfWeek, int startHour) {
        AppointmentSlot appointmentSlot = new AppointmentSlot(dayOfWeek, startHour, institution, doctor);
        em.persist(appointmentSlot);
        return appointmentSlot;
    }

    public AppointmentSlot getById(int id) {
        return em.find(AppointmentSlot.class, id);
    }

    public List<AppointmentSlot> getByDoctor(Doctor doctor) {
        final TypedQuery<AppointmentSlot> query = em.createQuery("FROM AppointmentSlot AS asl " +
                "WHERE asl.doctors_id = :doctor_id", AppointmentSlot.class);
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
}
