package ar.edu.itba.paw.webapp.filters;

import ar.edu.itba.paw.models.Loggable;
import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.webapp.auth.LoggedUserFinder;
import ar.edu.itba.paw.webapp.auth.Token;
import ar.edu.itba.paw.webapp.auth.UserAuthentication;
import ar.edu.itba.paw.webapp.params.LoginParams;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by alebian on 12/8/16.
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
    private static final String AUTH_HEADER_NAME = "Authorization";
    private DoctorService doctorService;
    private PatientService patientService;

    public StatelessAuthenticationFilter(final DoctorService doctorService, final PatientService patientService) {
        super();
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final Authentication authentication = getAuthentication(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private Authentication getAuthentication(final HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final String decoded = Token.decode(token);
            final Loggable user = LoggedUserFinder.getLoggedUser(
                    new LoginParams(Token.emailFromToken(decoded), Token.typeFromToken(decoded)),
                    doctorService,
                    patientService);

            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
