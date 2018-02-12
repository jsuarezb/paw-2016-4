package ar.edu.itba.paw.persistence.hibernate;


import ar.edu.itba.paw.persistence.AddressDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressHibernateDao implements AddressDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAllNeighborhoods() {
        final Query query = em.createQuery("SELECT DISTINCT neighborhood FROM Address");
        return query.getResultList();
    }
}
