package ex02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws Exception {
        if (args.length != 1)
            throw new Exception("Number of arguments greater than 1");
        Path path = Paths.get(args[0]);
        if (args[0].startsWith("--current-folder=")) {
            path = Paths.get(args[0].substring(17));
        }
        if (!Files.exists(path))
            throw new Exception("[Incorrect path] " + path);
        System.out.println(path);
        Scanner input = new Scanner(System.in);

        String commandLine  = "";
        while (!commandLine.equals("exit")) {
            commandLine = input.nextLine();
            String[] command = commandLine.split(" ");
            switch (command[0]) {
                case "mv":
                    CommandMv.run(path, command[1], command[2]);
                    break;
                case "ls":
                    CommandLs.run(path);
                    break;
                case "cd":
                    path = CommandCd.run(path, command[1]);
                    break;
                case "exit":
                    input.close();
                    break;
                default:
                    System.out.println("Uups... Unknown command!");
            }
        }
    }
}
