package school21.spring.service.repositories;

import school21.spring.service.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email) throws SQLException;
}
