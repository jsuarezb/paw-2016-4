package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.User;

/**
 * Created by socamica on 16/03/16.
 */
public interface UserDao {
    User create(final String username, final String password);
    User getByUsername(final String username);
    User getById(final Integer id);
}
