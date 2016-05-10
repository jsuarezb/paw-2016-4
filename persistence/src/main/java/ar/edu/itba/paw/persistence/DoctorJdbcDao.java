package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agophurmuz on 4/22/16.
 */
@Repository
public class DoctorJdbcDao implements DoctorDao {

    private static final String DOCTOR_TABLE_NAME = "doctors";
    private static final String DOCTORS_SPECIALITIES_TABLE_NAME = "doctorsSpecialities";
    private static final String APPOINTMENT_SLOT_TABLE_NAME = "AppointmentSlots";

    //Table Doctor
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String LAST_NAME_COL = "last_name";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    //Table doctorsSpecialities
    private static final String ID_DOCTOR_COL = "id_doctor";
    private static final String ID_SPECIALITY_COL = "id_speciality";
    //Table AppointmentSlots
    private static final String DOCTOR_COL = "doctor";
    private static final String INSTITUTION_COL = "institution";

    private JdbcTemplate jdbcTemplate;
    private DoctorRowMapper rowMapper;

    @Autowired
    private SpecialityDao specialityDao;

    @Autowired
    public DoctorJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        rowMapper = new DoctorRowMapper();
    }

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
        if(list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public Doctor getById(Integer id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", DOCTOR_TABLE_NAME, ID_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper, id);
        if(list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public List<Doctor> searchBySpeciality(Integer speciality_id) {
        String query = String.format("select * from %s where %s IN (select %s from %s where %s = ?)", DOCTOR_TABLE_NAME, ID_COL, ID_DOCTOR_COL, DOCTORS_SPECIALITIES_TABLE_NAME, ID_SPECIALITY_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper, speciality_id);
        if(list == null)
            return new ArrayList<Doctor>();

        return list;

    }

    public List<Doctor> getDoctorsByInstitution(Integer institution_id) {
        String query = String.format("select * from %s where %s IN (select distinct %s from %s where %s = ?)", DOCTOR_TABLE_NAME, ID_COL, DOCTOR_COL, APPOINTMENT_SLOT_TABLE_NAME, INSTITUTION_COL);
        List<Doctor> list = jdbcTemplate.query(query, rowMapper, institution_id);
        if (list == null)
            return new ArrayList<Doctor>();

        return list;
    }

    private class DoctorRowMapper implements RowMapper<Doctor>{

        public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {

            List<Speciality> specialities = specialityDao.getByDoctorId(rs.getInt(ID_COL));

            return new Doctor(rs.getInt(ID_COL), rs.getString(NAME_COL), rs.getString(LAST_NAME_COL), specialities, rs.getString(EMAIL_COL), rs.getString(PASSWORD_COL));
        }
    }
}
