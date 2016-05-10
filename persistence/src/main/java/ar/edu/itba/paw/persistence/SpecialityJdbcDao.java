package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by agophurmuz on 4/22/16.
 */

@Repository
public class SpecialityJdbcDao implements SpecialityDao {

    private static final String SPECIALITIES_TABLE_NAME = "specialities";
    private static final String DOCTORS_SPECIALITIES_TABLE_NAME = "doctorsSpecialities";

    //table specialities
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";

    //table doctorsSpecialities
    private static final String ID_DOCTOR_COL = "doctor_id";
    private static final String ID_SPECIALITY_COL = "speciality_id";

    private JdbcTemplate jdbcTemplate;
    private SpecialityRowMapper rowMapper;

    @Autowired
    public SpecialityJdbcDao(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        rowMapper = new SpecialityRowMapper();
    }

    public List<Speciality> getAll() {
        String query = String.format("SELECT * FROM %s ORDER BY %s", SPECIALITIES_TABLE_NAME, NAME_COL);
        List<Speciality> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return new ArrayList<Speciality>();
        }
        return list;
    }

    public Speciality searchByName(String name) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", SPECIALITIES_TABLE_NAME, NAME_COL);
        List<Speciality> list = jdbcTemplate.query(query, rowMapper, name);
        if (list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public Speciality getById(Integer id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", SPECIALITIES_TABLE_NAME, ID_COL);
        List<Speciality> list = jdbcTemplate.query(query, rowMapper, id);
        if (list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public Set<Speciality> getByDoctorId(Integer doctor_id) {
        String query = String.format("SELECT * FROM %s WHERE %s IN (SELECT %s FROM %s where %s = ?)", SPECIALITIES_TABLE_NAME, ID_COL,ID_SPECIALITY_COL, DOCTORS_SPECIALITIES_TABLE_NAME, ID_DOCTOR_COL);
        Set<Speciality> list = new HashSet<Speciality>(jdbcTemplate.query(query, rowMapper, doctor_id));

        if (list == null || list.isEmpty())
            return null;

        return list;
    }

    private static class SpecialityRowMapper implements RowMapper<Speciality>{

        public Speciality mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Speciality(rs.getInt(ID_COL), rs.getString(NAME_COL));
        }
    }
}
