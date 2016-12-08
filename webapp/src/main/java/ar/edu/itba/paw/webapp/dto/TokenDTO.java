package ar.edu.itba.paw.webapp.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement
public class TokenDTO {
    @XmlElement
    private String token;

    public TokenDTO() {
    }

    public TokenDTO(final String token) {
        this.token = token;
    }
}
