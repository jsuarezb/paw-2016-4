package ar.edu.itba.paw.models;

/**
 * Created by socamica on 16/03/16.
 */
public class User {
	private final int id;
    private final String username;
    private final String password;

    public User(final int id, final String username, final String password) {
    	this.id = id;
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

	public int getId() {
		return id;
	}
}
