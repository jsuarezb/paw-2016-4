package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.User;

/**
 * Created by socamica on 16/03/16.
 */
public interface UserDao {
    User create(final String email, final String password, final String firstName, final String lastName, final String phone);
    User findByEmail(final String email);
    User getById(final Integer id);
}
