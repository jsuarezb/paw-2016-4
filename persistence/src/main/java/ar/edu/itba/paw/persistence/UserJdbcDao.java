package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
@Repository
public class UserJdbcDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public UserJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("users")
			.usingGeneratedKeyColumns("id");
    }

    public User create(final String username, final String password) {
        final Map<String, Object> args = new HashMap<String, Object>();
        args.put("username", username);
        args.put("password", password);
        Number key = jdbcInsert.executeAndReturnKey(args);
        return new User(key.intValue(), username, password);
    }

    public User getByUsername(final String username) {
        final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE username = ? LIMIT 1", 
												   new UserRowMapper(),
												   username);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        }
    }

}
