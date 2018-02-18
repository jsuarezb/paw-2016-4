package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.Address;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement()
public class AddressDTO {
    @XmlElement
    private Integer id;
    @XmlElement
    private String street_name;
    @XmlElement
    private Integer street_number;
    @XmlElement
    private String apartment;
    @XmlElement
    private String city;
    @XmlElement
    private String country;
    @XmlElement
    private String state;
    @XmlElement
    private String neighborhood;
    @XmlElement
    private String lat;
    @XmlElement
    private String lon;

    public AddressDTO() {
    }

    public AddressDTO(final Address address) {
        this.id = address.getId();
        this.street_name = address.getStreetName();
        this.street_number = address.getStreetNumber();
        this.apartment = address.getApartment();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.state = address.getState();
        this.neighborhood = address.getNeighborhood();
        this.lat = address.getLat();
        this.lon = address.getLon();
    }

    public static List<AddressDTO> fromList(final List<Address> addresses) {
        if (addresses == null) {
            return Collections.emptyList();
        }
        return addresses.stream().map(a -> new AddressDTO(a)).collect(Collectors.toList());
    }
}
