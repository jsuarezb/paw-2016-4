package ar.edu.itba.paw.webapp.params;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecoverPasswordParams {

    @XmlElement
    public String password;

    @XmlElement
    public String passwordConfirmation;

    @XmlElement
    public String token;
}
