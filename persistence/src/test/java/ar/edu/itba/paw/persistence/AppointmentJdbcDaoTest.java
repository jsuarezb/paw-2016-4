package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AppointmentJdbcDaoTest {

    private static final String TABLE_NAME = "Appointments";

    private static final int PATIENT_ID = 1;
    private static final int DOCTOR_ID = 1;
    private static final int SLOT_ID = 1;
    private static final long DATE_MILLI = 1462344347561L;
    private static final String COMMENT = "Test comment";

    @Autowired
    private DataSource ds;

    @Autowired
    private AppointmentJdbcDao appointmentDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, TABLE_NAME);

        // TODO: populate corresponding tables
    }

}
