package ChaseLogic;

import java.util.*;
import Game.Game;

public class ChaseLogic {
    public ChaseLogic(Game game) {
        this.game = game;
    }

    public int[][] generatePathIndexes(Game.GamingMapCell target) {
        int[][] indexes = new int[game.width][game.height];

        for (int j = 0; j < game.height; j++) {
            for (int i = 0; i < game.width; i++) {
                indexes[i][j] = Math.abs(i - target.x) + Math.abs(j - target.y);
            }
        }
        return indexes;
    }

    public void printPathIndexes(int[][] pathIndexes) {
        for (int j = 0; j < game.height; j++) {
            for (int i = 0; i < game.width; i++) {
                System.out.printf("%d\t", pathIndexes[i][j]);
            }
            System.out.println();
        }
    }

    public void resetCells() {
        for (int j = 0; j < game.height; j++) {
            for (int i = 0; i < game.width; i++) {
                game.gamingMap[i][j].prevCell = null;
            }
        }
    }

    public Stack<Game.GamingMapCell> findPath(int[][] pathIndexes, Game.GamingMapCell start) {
        resetCells();

        List<Game.GamingMapCell> closed = new LinkedList<>();
        Queue<Game.GamingMapCell> open = new PriorityQueue<>(new Game.PathIndexComparator(pathIndexes));
        open.addAll(getAvailableRoutes(start, closed, start.symbol == game.settings.playerChar));

        while (!open.isEmpty()) {
            Game.GamingMapCell p = open.poll();

            if (closed.contains(p)) {
                continue;
            }
            if (pathIndexes[p.x][p.y] == 0) {
                return finalizeRoute(start, p);
            }
            closed.add(p);
            open.addAll(getAvailableRoutes(p, closed, start.symbol == game.settings.playerChar));
        }
        return null;
    }

    public Stack<Game.GamingMapCell> finalizeRoute(Game.GamingMapCell start, Game.GamingMapCell finish) {
        Stack<Game.GamingMapCell> route = new Stack<>();
        Game.GamingMapCell current = finish;
        while (current != start) {
            route.push(current);
            current = current.prevCell;
        }
        return route;
    }

    public List<Game.GamingMapCell> getAvailableRoutes(Game.GamingMapCell cell, List<Game.GamingMapCell> closed, boolean isPlayer) {
        List<Game.GamingMapCell> routes = new LinkedList<>();

        // Left
        if (cell.x - 1 >= 0
                && game.checkEmptyCell(game.gamingMap[cell.x - 1][cell.y], isPlayer)
                && !closed.contains(game.gamingMap[cell.x - 1][cell.y])) {
            routes.add(game.gamingMap[cell.x - 1][cell.y]);
        }
        // Up
        if (cell.y - 1 >= 0
                && game.checkEmptyCell(game.gamingMap[cell.x][cell.y - 1], isPlayer)
                && !closed.contains(game.gamingMap[cell.x][cell.y - 1])) {
            routes.add(game.gamingMap[cell.x][cell.y - 1]);
        }
        // Right
        if (cell.x + 1 < game.width
                && game.checkEmptyCell(game.gamingMap[cell.x + 1][cell.y], isPlayer)
                && !closed.contains(game.gamingMap[cell.x + 1][cell.y])) {
            routes.add(game.gamingMap[cell.x + 1][cell.y]);
        }
        // Down
        if (cell.y + 1 < game.height
                && game.checkEmptyCell(game.gamingMap[cell.x][cell.y + 1], isPlayer)
                && !closed.contains(game.gamingMap[cell.x][cell.y + 1])) {
            routes.add(game.gamingMap[cell.x][cell.y + 1]);
        }

        for (Game.GamingMapCell route : routes) {
            if (route.prevCell == null) {
                route.prevCell = cell;
            }
        }
        return routes;
    }

    Game game;
}
