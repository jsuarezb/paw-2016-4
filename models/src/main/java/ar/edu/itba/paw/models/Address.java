package ar.edu.itba.paw.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name = "addresses")
public class Address {

    private static final String PRINT_FORMAT = "%s %d, %s %s %s %s";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(sequenceName = "address_id_seq", name = "address_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(nullable = false)
    private Integer streetNumber;

    @Column(length = 100, nullable = true)
    private String apartment;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String state;

    @Column(length = 100, nullable = false)
    private String neighborhood;

    /* package */ Address() { }

    public Address(final String streetName, final Integer streetNumber, final String apartment,
                   final String city,  final String state, final String neighborhood, final String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public String getNeighborhood() {
        return neighborhood;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!streetName.equals(address.streetName)) return false;
        if (!streetNumber.equals(address.streetNumber)) return false;
        if (apartment != null ? !apartment.equals(address.apartment) : address.apartment != null) return false;
        if (!city.equals(address.city)) return false;
        if (!state.equals(address.state)) return false;
        return country.equals(address.country);

    }

    @Override
    public int hashCode() {
        int result = streetName.hashCode();
        result = 31 * result + streetNumber.hashCode();
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, streetName, streetNumber, apartment, city, state, country);
    }
}
