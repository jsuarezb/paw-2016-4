package ar.edu.itba.paw.webapp.params;

import ar.edu.itba.paw.models.Loggable;
import sun.rmi.runtime.Log;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alebian on 12/10/16.
 */
@XmlRootElement
public class LoginParams {
    @XmlElement
    public String email;
    @XmlElement
    public String password;
    @XmlElement
    public String type;

    public LoginParams() {
    }

    public LoginParams(final String email, final String type) {
        this.email = email;
        this.type = type;
    }
}
