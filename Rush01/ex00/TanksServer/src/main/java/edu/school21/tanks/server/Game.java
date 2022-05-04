package edu.school21.tanks.server;

import edu.school21.tanks.services.TanksService;

import java.util.LinkedList;

public class Game {
    TanksService tanksService;
    private final Long identifier;
    private final LinkedList<PlayerThread> players;
    private boolean isPlaying;

    public Game(TanksService tanksService) {
        this.tanksService = tanksService;
        this.identifier = tanksService.saveGame();
        this.players = new LinkedList<>();
        this.isPlaying = false;
    }

    public LinkedList<PlayerThread> getPlayers() {
        return this.players;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void play(PlayerThread player) {
        this.players.add(player);
//        while (this.players.size() < Game.MAX_PLAYERS) {
//            здесь происзодит ожидание пока оба игрока будут готовы
//        }
        this.isPlaying = true;
        player.addShot();
        player.addHit();
        System.out.println(player.getNickname() + " played all day long...");
        this.saveStats(player);
        this.isPlaying = false;
    }

    private void saveStats(PlayerThread player) {
        this.tanksService.saveStats(this.identifier, player.getNickname(), player.getShots(), player.getHits());
    }
}
