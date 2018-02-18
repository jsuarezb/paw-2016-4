package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.PagedResult;
import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.Normalizer;
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
                        "WHERE d.user.firstName = :name AND d.user.lastName = :last_name", Doctor.class);
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
    public List<Doctor> searchByName(final String name, final String lastName) {
        final TypedQuery<Doctor> query = em.createQuery(
                "SELECT d FROM Doctor AS d " +
                        "WHERE translate(lower(d.user.firstName), 'áéíóú', 'aeiou') LIKE lower(:name) " +
                        "AND translate(lower(d.user.lastName), 'áéíóú', 'aeiou') LIKE lower(:lastName) " +
                        "ORDER BY d.user.lastName, d.user.firstName ",
                Doctor.class
        );

        query.setParameter("name", "%" + Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") + "%");
        query.setParameter("lastName", "%" + Normalizer.normalize(lastName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") + "%");

        return query.getResultList();
    }

    @Override
    public PagedResult<Doctor> listAvailable(Integer specialityId, String neighborhood, Integer institutionId, Integer weekOfYear, Integer year, int page) {
        final StringBuilder baseQuery = new StringBuilder("FROM Doctor d WHERE d.id IN ")
                .append("(SELECT DISTINCT(asl.worksIn.doctor.id) FROM AppointmentSlot AS asl ")
                .append("   WHERE NOT EXISTS (SELECT ap.id FROM asl.appointments ap WHERE ap.weekNumber = :week_number AND ap.year = :year) ")
                .append("   AND (:institution_id IS NULL OR asl.worksIn.institution.id = :institution_id) ")
                .append("   AND (:neighborhood IS NULL OR :neighborhood = '' OR asl.worksIn.institution.address.neighborhood = :neighborhood) ")
                .append("   AND (:speciality_id = -1 OR :speciality_id = ANY (SELECT spec.id FROM asl.worksIn.doctor.specialities AS spec))) ");

        final StringBuilder rows = new StringBuilder("SELECT d ")
                .append(baseQuery)
                .append("ORDER BY d.id");

        final StringBuilder countRows = new StringBuilder("SELECT COUNT(d) ").append(baseQuery);

        final TypedQuery<Doctor> query = em.createQuery(rows.toString(), Doctor.class);

        query.setParameter("institution_id", institutionId);
        query.setParameter("neighborhood", neighborhood );
        query.setParameter("speciality_id", specialityId == null ? -1 : specialityId);
        query.setParameter("week_number", weekOfYear);
        query.setParameter("year", year);

        query.setMaxResults(DEFAULT_SIZE_OF_PAGE);
        query.setFirstResult(page * DEFAULT_SIZE_OF_PAGE);

        final List<Doctor> appointments = query.getResultList();

        final TypedQuery<Long> countQuery = em.createQuery(countRows.toString(), Long.class);
        countQuery.setParameter("institution_id", institutionId);
        countQuery.setParameter("neighborhood", neighborhood );
        countQuery.setParameter("speciality_id", specialityId == null ? -1 : specialityId);
        countQuery.setParameter("week_number", weekOfYear);
        countQuery.setParameter("year", year);

        final Long count = countQuery.getSingleResult();

        return new PagedResult<>(appointments, page, DEFAULT_SIZE_OF_PAGE, count);

    }

    @Override
    public boolean hasNextPageForSearchByName(final String name, final String lastName, final Integer page) {
        final TypedQuery<Doctor> query = em.createQuery(
                "SELECT d FROM Doctor AS d " +
                        "WHERE lower(d.user.firstName) LIKE lower(:name) AND lower(d.user.lastName) LIKE lower(:lastName) " +
                        "ORDER BY d.user.lastName, d.user.firstName ",
                Doctor.class
        );

        query.setFirstResult((page + 1) * DEFAULT_SIZE_OF_PAGE);
        query.setMaxResults(DEFAULT_SIZE_OF_PAGE);

        query.setParameter("name", "%" + name + "%");
        query.setParameter("lastName", "%" + lastName + "%");

        final int count = query.getResultList().size(); // Exception thrown when COUNT(*)ing

        return count > 0;
    }
}
