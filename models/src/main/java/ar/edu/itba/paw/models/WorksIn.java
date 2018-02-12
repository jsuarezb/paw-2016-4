package ar.edu.itba.paw.models;

import javax.persistence.*;

/**
 * Created by santi698 on 11/06/16.
 */
@Entity
@Table(name = "works_in")
public class WorksIn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "works_in_id_seq")
    @SequenceGenerator(sequenceName = "works_in_id_seq", name = "works_in_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Institution institution;

    /* package */ WorksIn() {}

    public WorksIn(Integer id, Doctor doctor, Institution institution) {
        this.id = id;
        this.doctor = doctor;
        this.institution = institution;
    }

    public Integer getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Institution getInstitution() {
        return institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorksIn worksIn = (WorksIn) o;

        return id.equals(worksIn.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "WorksIn{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", institution=" + institution +
                '}';
    }
}
