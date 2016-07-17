package ar.edu.itba.paw.webapp;

import ar.edu.itba.paw.webapp.filters.CORSResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by santi698 on 17/07/16.
 */
public class ChoPidoTurnosApi extends ResourceConfig {
    public ChoPidoTurnosApi() {
        register(CORSResponseFilter.class);
    }
}
