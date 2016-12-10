package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by alebian on 12/8/16.
 */
public class UserAuthentication implements Authentication {
    private Loggable user;
    private boolean authenticated;

    public UserAuthentication(Loggable user) {
        this.user = user;
        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Loggable getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getEmail();
    }
}

