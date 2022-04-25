package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        Statement statementUser = dataSource.getConnection().createStatement();
        ResultSet userSet = statementUser.executeQuery("SELECT * FROM chat_user WHERE id = " + 1);
        userSet.next();
        User user = new User(
                userSet.getInt("id"),
                userSet.getString("login"),
                userSet.getString("password"),
                new ArrayList<>(),
                new ArrayList<>()
        );
        userSet.close();

        User unrealUser = new User(
                0,
                "root",
                "admin",
                new ArrayList<>(),
                new ArrayList<>()
        );

        Statement statementRoom = dataSource.getConnection().createStatement();
        ResultSet roomSet = statementRoom.executeQuery("SELECT * FROM chat_room WHERE id = " + 1);
        roomSet.next();
        Chatroom room = new Chatroom(
                roomSet.getInt("id"),
                roomSet.getString("name"),
                user,
                new ArrayList<>()
        );
        roomSet.close();

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        Message message = new Message(0, user, room, "message message message message message", null);

        messagesRepository.save(message);

        Message messageTest = new Message(0, unrealUser, room, "unrealUser message unrealUser message unrealUser", null);

        messagesRepository.save(messageTest);
    }
}
