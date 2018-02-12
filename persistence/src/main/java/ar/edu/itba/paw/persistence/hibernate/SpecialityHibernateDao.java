package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Speciality;
import ar.edu.itba.paw.persistence.SpecialityDao;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by agophurmuz on 5/16/16.
 */
@Repository
public class SpecialityHibernateDao implements SpecialityDao {

    @PersistenceContext
    private EntityManager em;

    public List<Speciality> getAll() {
        final TypedQuery<Speciality> query = em.createQuery("FROM Speciality ", Speciality.class);
        return query.getResultList();
    }

    public Speciality getByName(final String name) {
        final TypedQuery<Speciality> query = em.createQuery("from Speciality  as s " +
                "where s.name = :name", Speciality.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Speciality getById(final Integer id) {
        return em.find(Speciality.class, id);
    }

    public List<Speciality> getByDoctorId(final Integer doctorId) {
        final TypedQuery<Speciality> query = em.createQuery(
                "SELECT ds FROM Doctor AS d " +
                "JOIN d.specialities AS ds " +
                "WHERE d.id = :doctor_id", Speciality.class);
        query.setParameter("doctor_id", doctorId);
        return query.getResultList();
    }

    @Override
    public Set<Speciality> getByInstitutionId(final Integer institution_id) {

        final TypedQuery<Doctor> query1= em.createQuery("FROM Doctor as d " +
                "WHERE d.id IN (select w.doctor.id from WorksIn as w " +
                                "WHERE w.institution.id = :institution_id)", Doctor.class);
        query1.setParameter("institution_id", institution_id);
        final List<Doctor> doctors = query1.getResultList();
        final Set<Speciality> allSpecialities = new HashSet<>();
        doctors.forEach(doctor -> allSpecialities.addAll(doctor.getSpecialities()));
        return allSpecialities;
    }
}
