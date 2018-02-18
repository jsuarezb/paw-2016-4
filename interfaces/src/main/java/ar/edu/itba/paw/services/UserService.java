package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.User;

/**
 * Created by socamica on 16/03/16.
 */
public interface UserService {
    User register(final String email, final String password, final String firstName, final String lastName,
                  final String phone);

    User findByEmail(final String username);

    User getById(final Integer id);

    boolean login(final String email, final String password);

    User setPassword(final User user, final String password);

    boolean sendPasswordRecoveryEmail(final String email, final String token);
}
