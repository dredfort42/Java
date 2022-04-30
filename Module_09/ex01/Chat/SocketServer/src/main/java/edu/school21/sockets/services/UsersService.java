package edu.school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    String signUp(String login,  String password) throws SQLException;
}
