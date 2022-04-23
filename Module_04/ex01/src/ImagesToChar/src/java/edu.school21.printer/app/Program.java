package ImagesToChar.src.java.edu.school21.printer.app;

import ImagesToChar.src.java.edu.school21.printer.logic.Logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Program {

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Number of arguments is incorrect");

        char whiteColor = args[0].charAt(0);
        char blackColor = args[1].charAt(0);
        Path path = Paths.get("/resources/it.bmp");

        if (!Files.isRegularFile(path))
            throw new Exception("Incorrect path to file");

        List<String> arrayFromFile = Logic.readImage(path, blackColor, whiteColor);

        for (String line : arrayFromFile)
            System.out.println(line);

    }
}