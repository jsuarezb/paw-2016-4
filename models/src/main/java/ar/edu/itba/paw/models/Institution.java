package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "institutions")
public class Institution {

    @Column(length = 100, nullable = false)
    private String name;

    @OneToOne
    private Address address;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institutions_id_seq")
    @SequenceGenerator(sequenceName = "institutions_id_seq", name = "institutions_id_seq", allocationSize = 1)
    private Integer id;

    /* package */ Institution(){ }

    public Institution(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Institution(String name, Address address, Integer id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Institution{" +
                "name='" + name + '\'' +
                //", address=" + address +
                '}';
    }
}
