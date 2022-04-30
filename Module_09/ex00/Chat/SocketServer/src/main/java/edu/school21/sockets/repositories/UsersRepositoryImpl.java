package edu.school21.sockets.repositories;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository{

    private JdbcTemplate jdbcTemplate;

    final String selectUserId =  "SELECT * FROM users WHERE user_id = ";
    final String selectAll =  "SELECT * FROM users";
    final String UpdateUser =  "UPDATE users SET ";
    final String DeleteUser =  "DELETE FROM users WHERE userid = ";
    final String selectUserName =  "SELECT * FROM users WHERE name = ";

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
        jdbcTemplate.update(String.format("INSERT INTO users VALUES ('%s', '%s', default);", entity.getEmail(), entity.getPassword()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UpdateUser + "name = " + '\'' + entity.getEmail() + '\'' + " WHERE user_id = " + entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DeleteUser + id);
    }

    @Override
    public Optional<User> findByEmail(String name) {
        return Optional.of(jdbcTemplate.queryForObject(selectUserName + name, new Object[]{name}, new BeanPropertyRowMapper<>(User.class)));
    }
}

