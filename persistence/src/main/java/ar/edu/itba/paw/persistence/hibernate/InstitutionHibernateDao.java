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
        final TypedQuery<Institution> query = em.createQuery("from Institution", Institution.class);
        return query.getResultList();
    }

    public Institution getById(final Integer id) {
        return em.find(Institution.class, id);
    }

    public List<Institution> getByName(final String name) {
        final TypedQuery<Institution> query = em.createQuery("from Institution as i " +
                "where i.name = :name", Institution.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
