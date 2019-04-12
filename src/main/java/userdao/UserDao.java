package userdao;

import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class UserDao {

    private final JejuJdbcTemplate jdbcTemplate;

    public UserDao(JejuJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public User get(Long id) {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        User result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            });
        } catch (EmptyResultDataAccessException e){

        }
        return result;
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo(name, password) values (?, ?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        return jdbcTemplate.insert(sql, params);
    }


    public void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);
    }


    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
