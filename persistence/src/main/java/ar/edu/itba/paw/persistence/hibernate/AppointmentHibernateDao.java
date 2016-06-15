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
import java.util.List;

@Repository
public class AppointmentHibernateDao implements AppointmentDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot, LocalDateTime startDate, String comments) {
        Appointment appointment = new Appointment(patient, appointmentSlot, startDate, comments);
        em.persist(appointment);
        return appointment;
    }
    //FIXME
    public List<Appointment> getAll() {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment", Appointment.class);
        return query.getResultList();
    }
    @Transactional
    public List<Appointment> getByDoctor(Doctor doctor) {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment AS a JOIN AppointmentSlot AS asl " +
                "ON a.appointment_slot_id = asl.id WHERE asl.doctor_id = :doctor_id", Appointment.class);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();

    }
    @Transactional
    public List<Appointment> getByPatient(Patient patient, int page) {
        final TypedQuery<Appointment> query = em.createQuery("FROM Appointment AS a " +
                "WHERE a.patient.id = :patient_id", Appointment.class);
        query.setParameter("patient_id", patient.getId());
        return query.getResultList();
    }
    @Transactional
    public boolean isDoctorAvailable(Doctor doctor, LocalDateTime date) {
        /*final Timestamp appointmentDate = new Timestamp(date.getMillis());
        final String query = String.format("SELECT COUNT(*) FROM %s WHERE %s <= ? AND %s >= ? AND  %s = ?",
                TABLE_NAME, START_DATE_COL, START_DATE_COL, DOCTOR_COL);*/
        return true;
    }

    @Transactional
    public boolean delete(int appointmentId) {
        Query query = em.createQuery("DELETE FROM Appointment WHERE id = :id");
        query.setParameter("id", appointmentId);
        return query.executeUpdate() == 1;
    }
}
