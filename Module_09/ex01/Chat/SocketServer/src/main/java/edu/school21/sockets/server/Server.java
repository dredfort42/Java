package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

@Component
public class Server {
    @Autowired
    private UsersService usersService;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Server() {
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Hello from Server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        String login, password;

        try {
            while (true) {
                out.println("Enter username:");
                login = in.readLine();
                out.println("Enter password:");
                password = in.readLine();
                if (login.isEmpty() || password.isEmpty()) {
                    out.println("Login and password can't be empty:");
                } else {
                    usersService.signUp(login, password);
                    out.println("Successful!");
                    stop();
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void interactionСlient() {
        String messageFromClient = null;
        try {
            while (true) {
                messageFromClient = in.readLine();
                if (messageFromClient.equals("signUp")) {
                    signUp();

                    out.println(messageFromClient);

                } else {
                    out.println("unrecognised command");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}