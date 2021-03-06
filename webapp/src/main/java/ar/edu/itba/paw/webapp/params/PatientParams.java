package ar.edu.itba.paw.webapp.params;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientParams {
    @XmlElement
    public String email;

    @XmlElement
    public String firstName;

    @XmlElement
    public String lastName;

    @XmlElement
    public String password;

    @XmlElement
    public String passwordConfirmation;
}
