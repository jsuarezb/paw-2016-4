package ar.edu.itba.paw.models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "specialities")
@XmlRootElement
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialities_id_seq")
    @SequenceGenerator(sequenceName = "specialities_id_seq", name = "specialities_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    public Speciality(){ }

    public Speciality(final String name){
        this.name = name;
    }

    public Speciality(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final Speciality speciality = (Speciality) o;

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
