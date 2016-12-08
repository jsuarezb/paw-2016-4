package ar.edu.itba.paw.webapp.filters;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.auth.Token;
import ar.edu.itba.paw.webapp.auth.UserAuthentication;
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
    private UserService userService;

    public StatelessAuthenticationFilter(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = getAuthentication(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final String username = Token.decode(token);
            final User user = userService.getByUsername(username);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
