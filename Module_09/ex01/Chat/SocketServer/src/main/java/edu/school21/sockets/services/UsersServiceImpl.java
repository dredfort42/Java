package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public String signUp(String login, String password) throws SQLException {
        User user = new User(login, password);
        usersRepository.save(user);
        return password;
    }
}
