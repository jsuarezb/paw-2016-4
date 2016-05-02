package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;
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

    private static final String DOCTOR_TABLE_NAME = "doctors";
    private static final String DOCTORS_SPECIALITIES_TABLE_NAME = "doctorsSpecialities";
    //Table Doctor
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String LAST_NAME_COL = "last_name";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    //Table doctorsSpecialities
    private static final String ID_DOCTOR_COL = "id_doctor";
    private static final String ID_SPECIALITY_COL = "id_speciality";

    private JdbcTemplate jdbcTemplate;
    private DoctorRowMapper rowMapper;

    public List<Doctor> getAll() {
        String query = String.format("SELECT * FROM %s", DOCTOR_TABLE_NAME);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return new ArrayList<Doctor>();
        }
        return list;
    }

    public Doctor searchByName(String name, String last_name) {
        String query = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?", DOCTOR_TABLE_NAME, NAME_COL, LAST_NAME_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper, name, last_name);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    public Doctor getById(Integer id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", DOCTOR_TABLE_NAME, ID_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper, id);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    public List<Doctor> searchBySpeciality(Integer speciality_id) {
        String query = String.format("select * from %s where %s IN (select %s from %s where %s = ?)", DOCTOR_TABLE_NAME, ID_COL, ID_DOCTOR_COL, DOCTORS_SPECIALITIES_TABLE_NAME, ID_SPECIALITY_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper, speciality_id);
        if(list == null){
            return new ArrayList<Doctor>();
        }
        return list;

    }


    private static class DoctorRowMapper implements RowMapper<Doctor>{

        public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Doctor(rs.getInt(ID_COL), rs.getString(NAME_COL), rs.getString(LAST_NAME_COL), "", rs.getString(EMAIL_COL), rs.getString(PASSWORD_COL));
        }
    }
}
