package ar.edu.itba.paw;

import ar.edu.itba.paw.persistence.SpecialityDao;
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
public class SpecialityJdbcDao implements SpecialityDao {

    private static final String TABLE_NAME = "specialities";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";

    private JdbcTemplate jdbcTemplate;
    private SpecialityRowMapper rowMapper;

    @Autowired
    public SpecialityJdbcDao(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
        rowMapper = new SpecialityRowMapper();
    }

    public List<Speciality> getAll() {
        String query = String.format("SELEC * FROM %s", TABLE_NAME);
        List<Speciality> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return new ArrayList<Speciality>();
        }
        return list;
    }

    public Speciality searchByName(String name) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, NAME_COL);
        List<Speciality> list = jdbcTemplate.query(query, rowMapper);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    public Speciality getById(Integer id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL);
        List<Speciality> list = jdbcTemplate.query(query, rowMapper, id);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    private static class SpecialityRowMapper implements RowMapper<Speciality>{

        public Speciality mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Speciality(rs.getInt(ID_COL), rs.getString(NAME_COL));
        }
    }
}
