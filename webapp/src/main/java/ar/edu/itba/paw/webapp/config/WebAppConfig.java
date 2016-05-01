package ar.edu.itba.paw.webapp.config;

import com.lyncode.jtwig.mvc.JtwigViewResolver;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@ComponentScan({"ar.edu.itba.paw.webapp.controllers",
                "ar.edu.itba.paw.persistence",
                "ar.edu.itba.paw.services"})
@EnableWebMvc
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    // Array of paths inside target/classes/
    private static final String[] schemaFiles = {
            "sql/schema_institution.sql",
            "sql/schema_doctors.sql",
            "sql/schema_specialities.sql",
            "sql/schema_doctors_specialities.sql"
    };

    @Bean
    public ViewResolver viewResolver() {
        JtwigViewResolver viewResolver = new JtwigViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".twig");
        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {
        final SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(Driver.class);
        ds.setUrl("jdbc:postgresql://localhost/paw_app");
        ds.setUsername("paw_app");
        ds.setPassword("paw_app");
        return ds;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource ds) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(ds);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        for (String path : schemaFiles)
            populator.addScripts(new ClassPathResource(path));
        return populator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(RESOURCES_LOCATION);
    }


}
