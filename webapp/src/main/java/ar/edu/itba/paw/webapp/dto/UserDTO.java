package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alebian on 12/8/16.
 */
@XmlRootElement()
public class UserDTO {
    @XmlElement
    private String username;

    @XmlElement
    private Integer id;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
    }

    public static List<UserDTO> fromList(List<User> users) {
        return users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }

}
