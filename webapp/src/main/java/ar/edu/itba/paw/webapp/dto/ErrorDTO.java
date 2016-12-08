package ar.edu.itba.paw.webapp.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement
public class ErrorDTO {
    @XmlElement
    private String errors;

    public ErrorDTO() {
    }

    public ErrorDTO(final String msg) {
        this.errors = msg;
    }
}
