package Game;

import ChaseLogic.ChaseLogic;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import java.util.*;

public class Game {
    public Game(GamingMapSettings settings) {
        if (settings.height * settings.width < settings.enemiesCount + settings.wallsCount + 2) {
            throw new IllegalParametersException();
        }

        this.settings = settings;
        this.height = settings.height;
        this.width = settings.width;
        this.gamingMap = new GamingMapCell[this.width][this.height];

        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                gamingMap[i][j] = new GamingMapCell(settings.emptyChar, settings.emptyColor, i, j);
            }
        }

        generateObjects(settings.wallChar, settings.wallColor, settings.wallsCount);
        generateObjects(settings.enemyChar, settings.enemyColor, settings.enemiesCount);
        generateObjects(settings.finishChar, settings.finishColor, 1);
        generateObjects(settings.playerChar, settings.playerColor, 1);

        chaseLogic = new ChaseLogic(this);

        goal = findObjects(settings.finishChar).get(0);
        goalIndexes = chaseLogic.generatePathIndexes(goal);
    }

    public void play() {
        while (true) {
            printToConsole();
            playPlayer();
            printToConsole();
            playEnemies();
        }
    }

    public void playPlayer() {
        player = findObjects(settings.playerChar).get(0);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Your turn (print: WASD or 9 for give up)");
            String input = scanner.nextLine();
            switch (input) {
                case "W":   // Up
                    if (player.y - 1 >= 0 && checkEmptyCell(gamingMap[player.x][player.y - 1], true)) {
                        move(player, gamingMap[player.x][player.y - 1]);
                        return;
                    } else {
                        System.out.println("You can't go there");
                    }
                    break;
                case "A":   // Left
                    if (player.x - 1 >= 0 && checkEmptyCell(gamingMap[player.x - 1][player.y], true)) {
                        move(player, gamingMap[player.x - 1][player.y]);
                        return;
                    } else {
                        System.out.println("You can't go there");
                    }
                    break;
                case "S":   // Down
                    if (player.y + 1 < height && checkEmptyCell(gamingMap[player.x][player.y + 1], true)) {
                        move(player, gamingMap[player.x][player.y + 1]);
                        return;
                    } else {
                        System.out.println("You can't go there");
                    }
                    break;
                case "D":   // Right
                    if (player.x + 1 < width && checkEmptyCell(gamingMap[player.x + 1][player.y], true)) {
                        move(player, gamingMap[player.x + 1][player.y]);
                        return;
                    } else {
                        System.out.println("You can't go there");
                    }
                    break;
                case "9":
                    System.out.println("GAME OVER!!! YOU LOSE!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    public void playEnemies() {
        player = findObjects(settings.playerChar).get(0);
        playerIndexes = chaseLogic.generatePathIndexes(player);
        List<GamingMapCell> enemies = findObjects(settings.enemyChar);

        System.out.println("Enemy turn");
        for (GamingMapCell enemy : enemies) {
            Stack<GamingMapCell> route = chaseLogic.findPath(playerIndexes, enemy);

            if (route != null && !route.isEmpty()) {
                move(enemy, route.pop());

                if (!settings.mode.equals("production")) {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        System.out.println("Confirm? (print: 8)");
                        String input = scanner.nextLine();
                        if (input.equals("8")) {
                            break;
                        } else {
                            System.out.println("Wrong input");
                        }
                    }
                }
            }
        }
    }

    public void move(GamingMapCell from, GamingMapCell to) {
        int gameOver = 0;
        if (from.symbol == settings.enemyChar && to.symbol == settings.playerChar) {
            gameOver = 1;
        } else if (from.symbol == settings.playerChar && to.symbol == settings.finishChar) {
            gameOver = 2;
        }

        to.symbol = from.symbol;
        from.symbol = settings.emptyChar;
        to.color = from.color;
        from.color = settings.emptyColor;

        if (!settings.mode.equals("production") || gameOver != 0) {
            printToConsole();
        }

        if (gameOver == 1) {
            System.out.println("GAME OVER!!! YOU LOSE!");
            System.exit(0);
        } else if (gameOver == 2) {
            System.out.println("GAME OVER!!! YOU WIN!");
            System.exit(0);
        }
    }

    public void generateObjects(char symbol, Ansi.BColor color, int count) {
        Random random = new Random();

        while (count > 0){
            int x = random.nextInt(this.width);
            int y = random.nextInt(this.height);

            if (gamingMap[x][y].symbol == settings.emptyChar) {
                gamingMap[x][y].symbol = symbol;
                gamingMap[x][y].color = color;
                count--;
            }
        }
    }

    public List<GamingMapCell> findObjects(char symbol) {
        List<GamingMapCell> list = new LinkedList<>();

        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                if (gamingMap[i][j].symbol == symbol) {
                    list.add(gamingMap[i][j]);
                }
            }
        }
        return list;
    }

    public boolean checkEmptyCell(GamingMapCell checkCell, boolean isPlayer) {
        if (checkCell.symbol == settings.emptyChar) {
            return true;
        }
        if (checkCell.symbol == settings.finishChar && isPlayer) {
            return true;
        }
        if (checkCell.symbol == settings.playerChar && !isPlayer) {
            return true;
        }
        return false;
    }

    public void printToConsole() {
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                cp.print(gamingMap[i][j].symbol, Ansi.Attribute.NONE, Ansi.FColor.NONE, gamingMap[i][j].color);
            }
            System.out.println();
        }
    }

    public static class GamingMapSettings {
        public GamingMapSettings(int height, int width, int enemiesCount, int wallsCount, String mode,
                                 char emptyChar, Ansi.BColor emptyColor,
                                 char playerChar, Ansi.BColor playerColor,
                                 char enemyChar, Ansi.BColor enemyColor,
                                 char wallChar, Ansi.BColor wallColor,
                                 char finishChar, Ansi.BColor finishColor) {
            this.height = height;
            this.width = width;
            this.enemiesCount = enemiesCount;
            this.wallsCount = wallsCount;
            this.mode = mode;
            this.emptyChar = emptyChar;
            this.emptyColor = emptyColor;
            this.playerChar = playerChar;
            this.playerColor = playerColor;
            this.enemyChar = enemyChar;
            this.enemyColor = enemyColor;
            this.wallChar = wallChar;
            this.wallColor = wallColor;
            this.finishChar = finishChar;
            this.finishColor = finishColor;
        }

        public int height;
        public int width;
        public int enemiesCount;
        public int wallsCount;
        public String mode;
        public char emptyChar;
        public Ansi.BColor emptyColor;
        public char playerChar;
        public Ansi.BColor playerColor;
        public char enemyChar;
        public Ansi.BColor enemyColor;
        public char wallChar;
        public Ansi.BColor wallColor;
        public char finishChar;
        public Ansi.BColor finishColor;
    }

    public static class GamingMapCell {
        public GamingMapCell(char symbol, Ansi.BColor color, int x, int y) {
            this.symbol = symbol;
            this.color = color;
            this.x = x;
            this.y = y;
            this.prevCell = null;
        }

        public char symbol;
        Ansi.BColor color;
        public int x;
        public int y;
        public GamingMapCell prevCell;
    }

    public static class PathIndexComparator implements Comparator<GamingMapCell> {
        public PathIndexComparator(int[][] pathIndexes) {
            this.pathIndexes = pathIndexes;
        }

        @Override
        public int compare(GamingMapCell cell, GamingMapCell t1) {
            if (pathIndexes[cell.x][cell.y] < pathIndexes[t1.x][t1.y]) {
                return -1;
            } else if (pathIndexes[cell.x][cell.y] > pathIndexes[t1.x][t1.y]) {
                return 1;
            }
            return 0;
        }

        private int[][] pathIndexes;
    }

    public static class IllegalParametersException extends RuntimeException {
        public IllegalParametersException() {
            super("Map to small for this objects");
        }
    }

    public GamingMapCell[][] gamingMap;
    public final int height;
    public final int width;
    int[][] goalIndexes;
    int[][] playerIndexes;
    GamingMapCell goal;
    GamingMapCell player;
    public GamingMapSettings settings;
    ChaseLogic chaseLogic;
}
