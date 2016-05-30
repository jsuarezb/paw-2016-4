package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    /* package */ User(){ }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [name=" + this.username + "]";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

	public Integer getId() {
		return id;
	}
}
