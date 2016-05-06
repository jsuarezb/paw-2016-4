package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Appointment;
import ar.edu.itba.paw.models.AppointmentSlot;
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
    public AppointmentJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        simpleJdbcInsert = new SimpleJdbcInsert(ds).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_COL);
        rowMapper = new AppointmentRowMapper();
    }

    public Appointment create(int patientId, int doctorId, int slotId, Date startDate, String comments) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put(PATIENT_COL, patientId);
        values.put(DOCTOR_COL, doctorId);
        values.put(SLOT_COL, slotId);
        values.put(START_DATE_COL, startDate);
        values.put(COMMENTS_COL, comments);

        int id = simpleJdbcInsert.executeAndReturnKey(values).intValue();

        AppointmentSlot slot = appointmentSlotDao.getById(slotId);

        return new Appointment(id, patientId, doctorId, slot, startDate, comments);
    }

    public List<Appointment> getByDoctor(int doctorId) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, DOCTOR_COL);
        List<Appointment> list = jdbcTemplate.query(query, rowMapper, doctorId);
        if (list == null)
            return new ArrayList<Appointment>();

        return list;
    }

    public List<Appointment> getByPatient(int patientId) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, PATIENT_COL);
        List<Appointment> list = jdbcTemplate.query(query, rowMapper, patientId);
        if (list == null)
            return new ArrayList<Appointment>();

        return list;
    }


    private class AppointmentRowMapper implements RowMapper<Appointment> {

        public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
            AppointmentSlot slot = appointmentSlotDao.getById(rs.getInt(SLOT_COL));

            Appointment appointment = new Appointment(
                    rs.getInt(ID_COL),
                    rs.getInt(PATIENT_COL),
                    rs.getInt(DOCTOR_COL),
                    null,
                    rs.getDate(START_DATE_COL),
                    rs.getString(COMMENTS_COL)
            );

            return appointment;
        }
    }

}
