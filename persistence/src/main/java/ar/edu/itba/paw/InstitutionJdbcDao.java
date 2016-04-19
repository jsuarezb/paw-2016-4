package ar.edu.itba.paw;

import ar.edu.itba.paw.persistence.InstitutionDao;
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
public class InstitutionJdbcDao implements InstitutionDao {

    private static final String TABLE_NAME = "Institution";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String STREET_NAME_COL = "street_name";
    private static final String STREET_NUMBER_COL = "street_number";
    private static final String APARTMENT_COL = "apartment";
    private static final String CITY_COL = "city";
    private static final String STATE_COL = "state";
    private static final String COUNTRY_COL = "country";

    private JdbcTemplate jdbcTemplate;
    private InstitutionRowMapper rowMapper;

    @Autowired
    public InstitutionJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        rowMapper = new InstitutionRowMapper();
    }

    public List<Institution> getAll() {
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Institution> list = jdbcTemplate.query(query, rowMapper);
        if (list == null)
            return new ArrayList<Institution>();

        return list;
    }

    public Institution getById(final Integer id) {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL);
        List<Institution> list = jdbcTemplate.query(query, rowMapper, id);
        if (list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    public List<Institution> searchByName(final String name) {
        String query = String.format("SELECT * FROM %s WHERE %s LIKE ?", TABLE_NAME, NAME_COL);
        List<Institution> list = jdbcTemplate.query(query, rowMapper, name);
        if (list == null)
            return new ArrayList<Institution>();

        return list;
    }

    private static class InstitutionRowMapper implements RowMapper<Institution> {

        public Institution mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address(
                    rs.getString(STREET_NAME_COL),
                    rs.getInt(STREET_NUMBER_COL),
                    rs.getString(APARTMENT_COL),
                    rs.getString(CITY_COL),
                    rs.getString(STATE_COL),
                    rs.getString(COUNTRY_COL)
            );

            return new Institution(rs.getInt(ID_COL), rs.getString(NAME_COL), address);
        }
    }

}
