package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.SpecialityDao;
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
public class SpecialityHibernateDao implements SpecialityDao {

    @PersistenceContext
    private EntityManager em;

    public List<Speciality> getAll() {
        final TypedQuery<Speciality> query = em.createQuery("from specialities", Speciality.class);
        return query.getResultList();
    }

    public Speciality getByName(String name) {
        final TypedQuery<Speciality> query = em.createQuery("from specialities as s " +
                "where s.name = :name", Speciality.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Speciality getById(Integer id) {
        return em.find(Speciality.class, id);
    }

    public List<Speciality> getByDoctorId(Integer doctorId) {
        final TypedQuery<Speciality> query = em.createQuery("from specialities as s join doctors_specialities as ds " +
                "on s.id = ds.doctor_id where ds.doctor_id = :doctor_id", Speciality.class);
        query.setParameter("doctor_id", doctorId);
        return query.getResultList();
    }
}
