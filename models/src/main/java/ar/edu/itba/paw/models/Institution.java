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
    private int id;


    /* package */ Institution(){ }

    public Institution(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
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
                ", address=" + address +
                '}';
    }
    public int getId() {
        return id;
    }
}
