package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Institution;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppointmentSlotJdbcDao implements AppointmentSlotDao {

    private static final String TABLE_NAME = "AppointmentSlots";

    private static final String ID_COL = "id";
    private static final String INSTITUTION_COL = "institution";
    private static final String DOCTOR_COL = "doctor";
    private static final String DAY_OF_WEEK_COL = "day_of_week";
    private static final String START_HOUR_COL = "start_hour";
    private static final int DAYS_IN_WEEK = 7;

    private JdbcTemplate jdbcTemplate;
    private AppointmentSlotRowMapper rowMapper;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private InstitutionDao institutionDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    public AppointmentSlotJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        simpleJdbcInsert = new SimpleJdbcInsert(ds).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_COL);
        rowMapper = new AppointmentSlotRowMapper();
    }

    public AppointmentSlot create(int institutionId, int doctorId, int dayOfWeek, int startHour) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put(INSTITUTION_COL, institutionId);
        values.put(DOCTOR_COL, doctorId);
        values.put(DAY_OF_WEEK_COL, dayOfWeek);
        values.put(START_HOUR_COL, startHour);

        int id = simpleJdbcInsert.executeAndReturnKey(values).intValue();

        return getById(id);
    }

    public AppointmentSlot getById(int id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL);
        List<AppointmentSlot> slots = jdbcTemplate.query(query, rowMapper, id);
        if (slots == null || slots.isEmpty())
            return null;

        return slots.get(0);
    }

    public List<AppointmentSlot> getByDoctor(int doctorId) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, DOCTOR_COL);
        List<AppointmentSlot> slots = jdbcTemplate.query(query, rowMapper, doctorId);
        if (slots == null)
            return new ArrayList<AppointmentSlot>();

        return slots;
    }

    public List<AppointmentSlot> getAvailableByDoctor(int doctorId, DateTime week) {
        String sql =
                "SELECT * FROM %s " +
                "WHERE %s NOT IN " +
                    "(SELECT %s FROM %s WHERE %s >= ? AND %s < ?)";

        String query = String.format(sql, TABLE_NAME, ID_COL, AppointmentJdbcDao.SLOT_COL,
                AppointmentJdbcDao.TABLE_NAME, AppointmentJdbcDao.START_DATE_COL,
                AppointmentJdbcDao.START_DATE_COL, DAYS_IN_WEEK);

        DateTime startOfWeek = week
                .withDayOfWeek(DateTimeConstants.MONDAY)
                .withTime(0, 0, 0, 0);

        DateTime endOfWeek = startOfWeek.plusDays(7);

        List<AppointmentSlot> slots = jdbcTemplate
                .query(query, rowMapper, new Date(startOfWeek.getMillis()), new Date(endOfWeek.getMillis()));

        if (slots == null)
            return new ArrayList<AppointmentSlot>();

        return slots;
    }

    public List<AppointmentSlot> getAvailableByDoctorInInstitution(int doctorId, int institutionId, DateTime week) {
        String sql =
                "SELECT * FROM %s " +
                        "WHERE %s = ? AND %s NOT IN " +
                        "(SELECT %s FROM %s WHERE %s >= ? AND %s < ?)";

        String query = String.format(sql, TABLE_NAME, INSTITUTION_COL, ID_COL, AppointmentJdbcDao.SLOT_COL,
                AppointmentJdbcDao.TABLE_NAME, AppointmentJdbcDao.START_DATE_COL,
                AppointmentJdbcDao.START_DATE_COL, DAYS_IN_WEEK);

        DateTime startOfWeek = week
                .withDayOfWeek(DateTimeConstants.MONDAY)
                .withTime(0, 0, 0, 0);

        DateTime endOfWeek = startOfWeek.plusDays(7);

        List<AppointmentSlot> slots = jdbcTemplate
                .query(query, rowMapper, institutionId, new Date(startOfWeek.getMillis()), new Date(endOfWeek.getMillis()));

        if (slots == null)
            return new ArrayList<AppointmentSlot>();

        return slots;
    }

    public List<AppointmentSlot> getByDoctorInInstitution(int doctorId, int institutionId) {
        String query = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?", TABLE_NAME, DOCTOR_COL, INSTITUTION_COL);
        List<AppointmentSlot> slots = jdbcTemplate.query(query, rowMapper, doctorId, institutionId);
        if (slots == null)
            return new ArrayList<AppointmentSlot>();

        return slots;
    }

    private class AppointmentSlotRowMapper implements RowMapper<AppointmentSlot> {

        public AppointmentSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
            Institution institution = institutionDao.getById(rs.getInt(INSTITUTION_COL));
            Doctor doctor = doctorDao.getById(rs.getInt(DOCTOR_COL));

            return new AppointmentSlot(
                    rs.getInt(ID_COL),
                    rs.getInt(DAY_OF_WEEK_COL),
                    rs.getInt(START_HOUR_COL),
                    institution,
                    doctor
            );
        }

    }

}
