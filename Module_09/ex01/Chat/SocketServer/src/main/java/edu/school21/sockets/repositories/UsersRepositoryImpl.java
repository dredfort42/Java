package edu.school21.sockets.repositories;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository{

    private JdbcTemplate jdbcTemplate;

    final String selectUserId =  "SELECT * FROM data WHERE id = ";
    final String selectAll =  "SELECT * FROM data";
    final String UpdateUser =  "UPDATE data SET ";
    final String DeleteUser =  "DELETE FROM data WHERE id = ";
    final String selectUserName =  "SELECT * FROM data WHERE uLogin = ";

    @Autowired
    public UsersRepositoryImpl(HikariDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(selectUserId + id, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
        List<User> list = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        return list;
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(String.format("INSERT INTO users VALUES ('%s', '%s', default);", entity.getLogin(), entity.getPassword()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UpdateUser + "uLogin = " + '\'' + entity.getLogin() + '\'' + " WHERE id = " + entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DeleteUser + id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.of(jdbcTemplate.queryForObject(selectUserName + login, new Object[]{login}, new BeanPropertyRowMapper<>(User.class)));
    }
}

