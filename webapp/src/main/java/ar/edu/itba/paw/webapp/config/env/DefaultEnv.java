package ar.edu.itba.paw.webapp.config.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by santi698 on 12/06/16.
 */
@Profile("default")
@PropertySource("classpath:config/production.properties")
@Configuration
public class DefaultEnv extends ProdEnv {
}
