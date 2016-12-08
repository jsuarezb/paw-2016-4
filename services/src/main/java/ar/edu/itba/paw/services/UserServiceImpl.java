package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by socamica on 16/03/16.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User register(final String username, final String password) {
        return userDao.create(username, password);
    }
    public User getByUsername(final String username) {
        return userDao.getByUsername(username);
    }
    public User getById(final Integer id) {
        return userDao.getById(id);
    }
}
