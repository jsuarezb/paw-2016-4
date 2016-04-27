package ar.edu.itba.paw;

import ar.edu.itba.paw.persistence.DoctorDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
public class DoctorJdbcDao implements DoctorDao {

    private static final String TABLE_NAME = "doctors";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String LAST_NAME_COL = "las_name";
    private static final String SPECIALITY_ID_COL = "speciality_id";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";

    private JdbcTemplate jdbcTemplate;
    private DoctorRowMapper rowMapper;

    public List<Doctor> getAll() {
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return new ArrayList<Doctor>();
        }
        return list;
    }

    public Doctor searchByName(String name, String last_name) {
        String query = String.format("SELECT * FROM %s WHERE name = %s AND last_name = %s", TABLE_NAME, NAME_COL, LAST_NAME_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    public Doctor getById(Integer id) {
        String query = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    public List<Doctor> searchBySpeciality(Integer speciality_id) {
        String query = String.format("SELECT * FROM %s WHERE speciality_id = %d", TABLE_NAME, speciality_id);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return new ArrayList<Doctor>();
        }
        return list;

    }


    private static class DoctorRowMapper implements RowMapper<Doctor>{

        public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Doctor(rs.getInt(ID_COL), rs.getString(NAME_COL), rs.getString(LAST_NAME_COL), rs.getInt(SPECIALITY_ID_COL), rs.getString(EMAIL_COL), rs.getString(PASSWORD_COL));
        }
    }
}
