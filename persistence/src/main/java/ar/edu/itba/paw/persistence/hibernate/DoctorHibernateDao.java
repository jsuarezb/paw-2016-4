package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DoctorHibernateDao implements DoctorDao {

    @PersistenceContext
    private EntityManager em;

    public List<Doctor> getAll() {
        final TypedQuery<Doctor> query = em.createQuery("from Doctor", Doctor.class);
        return query.getResultList();
    }

    public Doctor getById(Integer id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> getBySpeciality(Integer specialityId) {
        final TypedQuery<Doctor> query = em.createQuery("from Doctor as d join doctors_specialities as ds " +
                "on d.id = ds.doctor_id" +
                "where ds.speciality_id = :speciality_id", Doctor.class);
        query.setParameter("speciality_id", specialityId);
        return query.getResultList();
    }

    public List<Doctor> getDoctorsByInstitution(Integer institutionId) {
        final TypedQuery<Doctor> query = em.createQuery("from Doctor as d " +
                "where d.institution_id = :institution_id", Doctor.class);
        query.setParameter("institution_id", institutionId);
        return query.getResultList();
    }

    public Doctor getByName(String name, String lastName) {
        final TypedQuery<Doctor> query = em.createQuery("FROM Doctor as d " +
                "WHERE d.name = :name AND d.last_name = :last_name", Doctor.class);
        query.setParameter("name", name);
        query.setParameter("last_name", lastName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
