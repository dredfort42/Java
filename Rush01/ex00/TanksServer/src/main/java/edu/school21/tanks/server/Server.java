package edu.school21.tanks.server;

import edu.school21.tanks.services.TanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

@Component
public class Server {
    @Value("${server.max.players}")
    public int serverMaxPlayers;
    private final TanksService tanksService;
    public static LinkedList<PlayerThread> clientList = new LinkedList<>();
    public static LinkedList<Game> gameList = new LinkedList<>();

    @Autowired
    public Server(TanksService tanksService) {
        this.tanksService = tanksService;
    }

    public void run(int port) {
        int count = 0;

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server runs with port=" + server.getLocalPort());

            while (count++ < this.serverMaxPlayers) {
                Server.printTotalPlayers();
                Socket socket = server.accept();

                try {
                    clientList.add(new PlayerThread(socket, tanksService));
                } catch (GameException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTotalPlayers() {
        System.out.println(clientList.size() + " total players connected");
    }
}
