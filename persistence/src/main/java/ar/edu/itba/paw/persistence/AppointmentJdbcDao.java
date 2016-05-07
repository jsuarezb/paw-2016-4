package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppointmentJdbcDao implements AppointmentDao {

    public static final String TABLE_NAME = "Appointments";
    public static final String ID_COL = "id";
    public static final String PATIENT_COL = "patient";
    public static final String DOCTOR_COL = "doctor";
    public static final String SLOT_COL = "slot";
    public static final String START_DATE_COL = "start_date";
    public static final String COMMENTS_COL = "comments";

    private JdbcTemplate jdbcTemplate;
    private AppointmentRowMapper rowMapper;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private AppointmentSlotDao appointmentSlotDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    public AppointmentJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        simpleJdbcInsert = new SimpleJdbcInsert(ds).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_COL);
        rowMapper = new AppointmentRowMapper();
    }

    public Appointment create(int patientId, int doctorId, int slotId, DateTime startDate, String comments) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put(PATIENT_COL, patientId);
        values.put(DOCTOR_COL, doctorId);
        values.put(SLOT_COL, slotId);
        values.put(START_DATE_COL, startDate.toDate());
        values.put(COMMENTS_COL, comments);

        int id = simpleJdbcInsert.executeAndReturnKey(values).intValue();

        Appointment appointment = getById(id);

        return appointment;
    }

    public Appointment getById(int id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL);
        List<Appointment> list = jdbcTemplate.query(query, rowMapper, id);
        if (list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public List<Appointment> getByDoctor(int doctorId) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, DOCTOR_COL);
        List<Appointment> list = jdbcTemplate.query(query, rowMapper, doctorId);
        if (list == null)
            return new ArrayList<Appointment>();

        return list;
    }

    public List<Appointment> getByPatient(int patientId, int page) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, PATIENT_COL);
        List<Appointment> list = jdbcTemplate.query(query, rowMapper, patientId);
        if (list == null)
            return new ArrayList<Appointment>();

        return list;
    }

    public boolean isDoctorAvailable(int doctorId, DateTime date) {
        final Timestamp appointmentDate = new Timestamp(date.getMillis());
        final String query = String.format("SELECT COUNT(*) FROM %s WHERE %s <= ? AND %s >= ? AND  %s = ?",
                TABLE_NAME, START_DATE_COL, START_DATE_COL, DOCTOR_COL);

        final Integer count = jdbcTemplate
                .queryForObject(query, Integer.class, appointmentDate, appointmentDate, doctorId);

        return count != null && count == 0;
    }

    public int delete(int appointmentId) {
        final String query = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, ID_COL);

        return jdbcTemplate.update(query, appointmentId);
    }

    private class AppointmentRowMapper implements RowMapper<Appointment> {

        public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Patient patient = patientDao.getById(rs.getInt(PATIENT_COL));
            final Doctor doctor = doctorDao.getById(rs.getInt(DOCTOR_COL));
            final AppointmentSlot slot = appointmentSlotDao.getById(rs.getInt(SLOT_COL));
            final DateTime jDate = new DateTime(rs.getTimestamp(START_DATE_COL));

            return new Appointment(
                    rs.getInt(ID_COL),
                    patient,
                    doctor,
                    slot,
                    jDate,
                    rs.getString(COMMENTS_COL)
            );
        }
    }

}
