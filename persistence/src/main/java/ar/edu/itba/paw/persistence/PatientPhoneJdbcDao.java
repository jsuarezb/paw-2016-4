package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.PatientPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class PatientPhoneJdbcDao implements PatientPhoneDao {

    private static final String TABLE_NAME = "PatientsPhones";

    private static final String PATIENT_COL = "patient_id";
    private static final String PHONE_COL = "phone";

    private JdbcTemplate jdbcTemplate;
    private PatientPhoneRowMapper rowMapper;

    @Autowired
    public PatientPhoneJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        rowMapper = new PatientPhoneRowMapper();
    }

    public List<PatientPhone> getByPatientId(int id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, PATIENT_COL);
        List<PatientPhone> list = jdbcTemplate.query(query, rowMapper, id);
        if (list == null)
            return Collections.emptyList();

        return list;
    }

    private static final class PatientPhoneRowMapper implements RowMapper<PatientPhone> {

        public PatientPhone mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PatientPhone(
                    rs.getInt(PATIENT_COL),
                    rs.getString(PHONE_COL)
            );
        }
    }
}
