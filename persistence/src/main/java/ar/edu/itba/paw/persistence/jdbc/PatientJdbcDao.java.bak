package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.persistence.PatientDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientJdbcDao implements PatientDao {

    private static final String TABLE_NAME = "patients";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String LAST_NAME_COL = "last_name";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private PatientRowMapper rowMapper;

    public PatientJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        simpleJdbcInsert = new SimpleJdbcInsert(ds).withTableName(TABLE_NAME).usingGeneratedKeyColumns("id");
        rowMapper = new PatientRowMapper();
    }

    public List<Patient> getAll() {
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Patient> list = jdbcTemplate.query(query, rowMapper);
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    public Patient getById(final Integer id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL);
        List<Patient> list = jdbcTemplate.query(query, rowMapper, id);
        if (list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public Patient create(String name, String last_name, String email, String password) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", name);
        values.put("last_name", last_name);
        values.put("email", email);
        values.put("password", password);

        int id = simpleJdbcInsert.executeAndReturnKey(values).intValue();

        return new Patient(id, name, last_name, email, password);
    }

    public Patient getByEmail(final String email) {
        String query = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME, EMAIL_COL, email);
        List<Patient> list = jdbcTemplate.query(query, rowMapper);
        if (list == null || list.size() == 0)
            return null;
        if (list.size() > 1) {
            throw new RuntimeException("Database inconsistency");
        }
        return list.get(0);
    }

    private class PatientRowMapper implements RowMapper<Patient> {

        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Patient(
                    rs.getInt(ID_COL),
                    rs.getString(NAME_COL),
                    rs.getString(LAST_NAME_COL),
                    rs.getString(EMAIL_COL),
                    rs.getString(PASSWORD_COL)
            );
        }
    }

}
