package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by socamica on 16/03/16.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User register(String email, String password, String firstName, String lastName, String phone) {
        return userDao.create(email, password, firstName, lastName, phone);
    }

    public User findByEmail(final String email) {
        return userDao.findByEmail(email);
    }
    public User getById(final Integer id) {
        return userDao.getById(id);
    }

    @Override
    public boolean login(String email, String password) {
        final User user = findByEmail(email);
        if (user != null) {
            return password.equals(user.getPassword());
        }
        return false;
    }
}

