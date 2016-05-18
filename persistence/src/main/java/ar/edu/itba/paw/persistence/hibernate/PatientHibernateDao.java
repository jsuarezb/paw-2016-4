package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.persistence.PatientDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by agophurmuz on 5/16/16.
 */
@Repository
public class PatientHibernateDao implements PatientDao {

    @PersistenceContext
    private EntityManager em;

    public List<Patient> getAll() {
        final TypedQuery<Patient> query = em.createQuery("from patients", Patient.class);
        return query.getResultList();
    }

    public Patient getById(Integer id) {
        return em.find(Patient.class, id);
    }

    public Patient getByEmail(String email) {
        final TypedQuery<Patient> query = em.createQuery("from patients as p where p.email = :email", Patient.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Patient create(String name, String lastName, String email, String password) {
        Patient patient = new Patient(name, lastName, email, password);
        em.persist(patient);
        return patient;
    }
}
