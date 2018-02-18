package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.persistence.UserDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

/**
 * Created by agophurmuz on 5/18/16.
 */
@Repository
public class UserHibernateDao implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User create(String email, String password, String firstName, String lastName, String phone) {
        final Patient patient = new Patient();
        em.persist(patient);
        final User user = new User(firstName, lastName, email, password, phone, patient, null);
        em.persist(user);
        return user;
    }

    public User findByEmail(String email) {
        final TypedQuery<User> query = em.createQuery("from User as u where u.email = :email", User.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getById(final Integer id) {
        final TypedQuery<User> query = em.createQuery("from User as u where u.id = :id", User.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public User setPassword(final User user, final String password) {
        final Query query = em.createQuery("UPDATE User u SET u.password = :password WHERE u.id = :id");
        query.setParameter("password", password);
        query.setParameter("id", user.getId());
        query.executeUpdate();
        user.setPassword(password);
        return user;
    }
}
