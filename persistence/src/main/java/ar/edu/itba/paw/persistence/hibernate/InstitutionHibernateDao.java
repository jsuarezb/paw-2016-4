package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Institution;
import ar.edu.itba.paw.persistence.InstitutionDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by agophurmuz on 5/16/16.
 */
@Repository
public class InstitutionHibernateDao implements InstitutionDao {

    @PersistenceContext
    private EntityManager em;

    public List<Institution> getAll() {
        final TypedQuery<Institution> query = em.createQuery("from institutions", Institution.class);
        return query.getResultList();
    }

    public Institution getById(Integer id) {
        return em.find(Institution.class, id);
    }

    public List<Institution> getByName(String name) {
        final TypedQuery<Institution> query = em.createQuery("from institutions as i " +
                "where i.name = :name", Institution.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
