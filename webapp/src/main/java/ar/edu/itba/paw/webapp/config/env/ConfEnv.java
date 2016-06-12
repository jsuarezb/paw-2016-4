package ar.edu.itba.paw.webapp.config.env;

import javax.sql.DataSource;

/**
 * Created by santi698 on 12/06/16.
 */
public interface ConfEnv {
    public DataSource createDataSource();
}
