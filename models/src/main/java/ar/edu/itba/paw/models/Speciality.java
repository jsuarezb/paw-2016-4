package ar.edu.itba.paw.models;


import javax.persistence.*;

@Entity
@Table(name = "specialities")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialities_id_seq")
    @SequenceGenerator(sequenceName = "specialities_id_seq", name = "specialities_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    /* package */ Speciality(){ }

    public Speciality(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Speciality speciality = (Speciality) o;

        return id == speciality.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
