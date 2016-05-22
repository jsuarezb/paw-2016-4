package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.persistence.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by agophurmuz on 5/18/16.
 */
@Repository
public class UserHibernateDao implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public User create(String username, String password) {
        User user = new User(username, password);
        em.persist(user);
        return user;
    }

    public User getByUsername(String username) {
        final TypedQuery<User> query = em.createQuery("from users as u where u.username = :username", User.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
