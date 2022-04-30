package edu.school21.sockets.repositories;


import edu.school21.sockets.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByLogin(String login) throws SQLException;
}
