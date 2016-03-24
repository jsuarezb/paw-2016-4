package ar.edu.itba.paw;

/**
 * Created by santi698 on 24/03/16.
 */
public class Address {
    private final String streetName;
    private final Integer streetNumber;
    private final String apartment;
    private final String city;
    private final String state;
    private final String country;

    public Address(String streetName, Integer streetNumber, String apartment, String city, String state, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
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

    @Override
    public boolean equals(Object o) {
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
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", apartment='" + apartment + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
