package ar.edu.itba.paw.services;

import ar.edu.itba.paw.User;

/**
 * Created by socamica on 16/03/16.
 */
public interface UserService {
    User register(final String username, final String password);

    User getByUsername(String username);
}
