package edu.school21.tanks.server;

import edu.school21.tanks.services.TanksService;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.Socket;

public class PlayerThread extends Thread {
    @Value("${game.max.players}")
    public int gameMaxPlayers;
    private final String STOP = "Exit";
    private final Socket socket;
    private final TanksService tanksService;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final String nickname;
    private Game game;
    private int shots;
    private int hits;

    public PlayerThread(Socket socket, TanksService tanksService) throws IOException, GameException {
        this.socket = socket;
        this.tanksService = tanksService;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        send("Enter your nickname");

        this.nickname = this.in.readLine();
        this.game = null;
        this.shots = 0;
        this.hits = 0;

        sendAll("joined the server");

        start();
    }

    @Override
    public void run() {
        try {
            String message;

            send("Press enter to search for a game");

            while (!this.in.readLine().equalsIgnoreCase(STOP)) {
                synchronized (Server.class) {
                    for (Game g : Server.gameList) {
                        if (g.getPlayers().size() < this.gameMaxPlayers) {
                            this.game = g;
                            send("Joined existing game, press enter to continue");
                        }
                    }

                    if (this.game == null) {
                        this.game = new Game(this.tanksService);
                        send("New game created, press enter to continue");
                    }
                }

                while (!(message = this.in.readLine()).equalsIgnoreCase(STOP)) {
                    send(message);

                    this.game.play(this);
                }
            }

            closeConnection();
        } catch (IOException ignored) {}
    }

    public String getNickname() {
        return this.nickname;
    }

    public void addShot() {
        this.shots++;
    }

    public int getShots() {
        return this.shots;
    }

    public void addHit() {
        this.hits++;
    }

    public int getHits() {
        return this.hits;
    }

    public void resetStats() {
        this.shots = 0;
        this.hits = 0;
    }

    private void sendAll(String message) {
        System.out.println("[General] " + this.nickname + ": " + message);

        for (PlayerThread playerThread : Server.clientList) {
            playerThread.send(message);
        }
    }

    private void sendGame(Game game, String message) {
        for (PlayerThread playerThread : game.getPlayers()) {
            message = "[" + playerThread.getNickname() + "] " + this.nickname + ": " + message;
            System.out.println(message);
            playerThread.send(message);
        }

        this.send(message);
    }

    private void send(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

    private void closeConnection() {
        try {
            if (!socket.isClosed()) {
                this.send(STOP);
                this.socket.close();
                this.in.close();
                this.out.close();
            }

            Server.clientList.remove(this);
            this.sendAll("left the server");
            Server.printTotalPlayers();
        } catch (IOException ignored) {}
    }

    private void gameError(String error) throws GameException {
        System.err.println(error);
        this.closeConnection();
        throw new GameException();
    }
}

class GameException extends Exception {}
