package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.services.DoctorService;
import ar.edu.itba.paw.services.PatientService;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.filters.StatelessAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.webapp.config")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
    public static final String APP_KEY = "5167nPYHKm5KTvFrSIkseTNJaLLAZOfB2K05/bmlbUI=";  // Created using openssl

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    public WebAuthConfig() {
        super(true);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().permitAll().and()
            .addFilterBefore(new StatelessAuthenticationFilter(doctorService, patientService),
                                UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().and()
            .anonymous().and()
            .servletApi();
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
}
