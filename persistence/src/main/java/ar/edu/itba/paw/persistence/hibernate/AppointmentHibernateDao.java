package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.persistence.AppointmentDao;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by agophurmuz on 5/16/16.
 */
@Repository
public class AppointmentHibernateDao implements AppointmentDao {

    @PersistenceContext
    private EntityManager em;

    public Appointment create(Patient patient, Doctor doctor, AppointmentSlot appointmentSlot, DateTime startDate, String comments) {
        Appointment appointment = new Appointment(patient, doctor, appointmentSlot, startDate, comments);
        em.persist(appointment);
        return appointment;
    }

    public List<Appointment> getByDoctor(Doctor doctor) {
        final TypedQuery<Appointment> query = em.createQuery("FROM appointments AS a JOIN appointment_slot AS asl " +
                "ON a.appointment_slot_id = asl.id WHERE asl.doctor_id = :doctor_id", Appointment.class);
        query.setParameter("doctor_id", doctor.getId());
        return query.getResultList();

    }

    public List<Appointment> getByPatient(Patient patient, int page) {
        final TypedQuery<Appointment> query = em.createQuery("FROM appointments AS a JOIN patients AS p " +
                "ON p.patients_id = p.id WHERE p.id = :patient_id", Appointment.class);
        query.setParameter("patient_id", patient.getId());
        return query.getResultList();
    }

    public boolean isDoctorAvailable(Doctor doctor, DateTime date) {
        /*final Timestamp appointmentDate = new Timestamp(date.getMillis());
        final String query = String.format("SELECT COUNT(*) FROM %s WHERE %s <= ? AND %s >= ? AND  %s = ?",
                TABLE_NAME, START_DATE_COL, START_DATE_COL, DOCTOR_COL);*/
        return false;
    }

    public boolean delete(int appointmentId) {
        Query query = em.createQuery("DELETE FROM appointments WHERE id = :id");
        query.setParameter("id", appointmentId);
        return query.executeUpdate() == 1;
    }
}
