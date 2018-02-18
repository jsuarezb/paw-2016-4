package ar.edu.itba.paw.webapp.params;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement
public class UserParams {
    @XmlElement
    public String email;
    @XmlElement
    public String password;
    @XmlElement
    public String passwordConfirmation;
    @XmlElement
    public String firstName;
    @XmlElement
    public String lastName;
    @XmlElement
    public String phone;
}
