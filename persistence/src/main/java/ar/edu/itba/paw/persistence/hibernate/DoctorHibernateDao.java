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

    private static final int DEFAULT_SIZE_OF_PAGE = 15;

    @PersistenceContext
    private EntityManager em;

    public List<Doctor> getAll() {
        final TypedQuery<Doctor> query = em.createQuery("from Doctor", Doctor.class);
        return query.getResultList();
    }

    public Doctor getById(final Integer id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> getBySpeciality(final Integer specialityId) {
        final TypedQuery<Doctor> query = em.createQuery(
                "SELECT d FROM Doctor AS d" +
                        " JOIN d.specialities AS ds" +
                        " WHERE ds.speciality_id = :speciality_id", Doctor.class);
        query.setParameter("speciality_id", specialityId);
        return query.getResultList();
    }

    public List<Doctor> getDoctorsByInstitution(final Integer institutionId) {
        final TypedQuery<Doctor> query = em.createQuery(
                "SELECT d FROM WorksIn as worksIn" +
                        " JOIN worksIn.doctor as d" +
                        " WHERE worksIn.institution.id = :institution_id", Doctor.class);
        query.setParameter("institution_id", institutionId);
        return query.getResultList();
    }

    public Doctor getByName(final String name, final String lastName) {
        final TypedQuery<Doctor> query = em.createQuery(
                "FROM Doctor AS d " +
                        "WHERE d.name = :name AND d.lastName = :last_name", Doctor.class);
        query.setParameter("name", name);
        query.setParameter("last_name", lastName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Doctor getByEmail(final String email) {
        final TypedQuery<Doctor> query = em.createQuery("from Doctor as p where p.email = :email", Doctor.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Doctor> searchByName(final String name, final String lastName, final Integer page) {
        final TypedQuery<Doctor> query = em.createQuery(
                "FROM Doctor AS d " +
                        "WHERE lower(d.name) LIKE lower(:name) AND lower(d.lastName) LIKE lower(:lastName) " +
                        "ORDER BY d.lastName, d.name ",
                Doctor.class
        );

        query.setFirstResult(page * DEFAULT_SIZE_OF_PAGE);
        query.setMaxResults(DEFAULT_SIZE_OF_PAGE);

        query.setParameter("name", "%" + name + "%");
        query.setParameter("lastName", "%" + lastName + "%");

        return query.getResultList();
    }

    @Override
    public boolean hasNextPageForSearchByName(final String name, final String lastName, final Integer page) {
        final TypedQuery<Doctor> query = em.createQuery(
                "FROM Doctor AS d " +
                        "WHERE lower(d.name) LIKE lower(:name) AND lower(d.lastName) LIKE lower(:lastName) " +
                        "ORDER BY d.lastName, d.name ",
                Doctor.class
        );

        query.setFirstResult((page + 1) * DEFAULT_SIZE_OF_PAGE);
        query.setMaxResults(DEFAULT_SIZE_OF_PAGE);

        query.setParameter("name", "%" + name + "%");
        query.setParameter("lastName", "%" + lastName + "%");

        int count = query.getResultList().size(); // Exception thrown when COUNT(*)ing

        return count > 0;
    }
}
