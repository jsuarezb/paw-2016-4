package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class CPTUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientService ps;

    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        final Patient patient = ps.findByEmail(email);
        if (patient != null) {
            final Collection<GrantedAuthority> authorities = new HashSet<>();
            return new org.springframework.security.core.userdetails.User(
                    patient.getEmail(), patient.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("No user found by the email " + email);
    }

}