package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.DoctorPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorPhoneJdbcDao implements DoctorPhoneDao {

    private static final String TABLE_NAME = "DoctorsPhones";

    private static final String DOCTOR_COL = "doctor_id";
    private static final String PHONE_COL = "phone";

    private JdbcTemplate jdbcTemplate;
    private DoctorPhoneRowMapper rowMapper;

    @Autowired
    public DoctorPhoneJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        rowMapper = new DoctorPhoneRowMapper();
    }

    public List<DoctorPhone> getByDoctorId(final int doctorId) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, DOCTOR_COL);
        List<DoctorPhone> list = jdbcTemplate.query(query, rowMapper, doctorId);
        if (list == null)
            return new ArrayList<DoctorPhone>();

        return list;
    }

    private static final class DoctorPhoneRowMapper implements RowMapper<DoctorPhone> {

        public DoctorPhone mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DoctorPhone(
                    rs.getInt(DOCTOR_COL),
                    rs.getString(PHONE_COL)
            );
        }
    }

}
