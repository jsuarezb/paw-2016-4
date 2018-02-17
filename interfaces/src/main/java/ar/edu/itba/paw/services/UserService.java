package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.User;

import java.util.List;

/**
 * Created by socamica on 16/03/16.
 */
public interface UserService {
    User register(final String email, final String password, final String firstName, final String lastName,
                  final String phone);

    User findByEmail(final String username);

    User getById(final Integer id);

    boolean login(final String email, final String password);

}
