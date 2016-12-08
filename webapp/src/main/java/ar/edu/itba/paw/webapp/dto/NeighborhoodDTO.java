package ar.edu.itba.paw.webapp.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement
public class NeighborhoodDTO {
    @XmlElement
    private String name;

    public NeighborhoodDTO() {
    }

    public NeighborhoodDTO(final String name) {
        this.name = name;
    }

    public static List<NeighborhoodDTO> fromList(final List<String> neighborhoods) {
        if (neighborhoods == null) {
            return Collections.emptyList();
        }
        return neighborhoods.stream().map(n -> new NeighborhoodDTO(n)).collect(Collectors.toList());
    }
}
