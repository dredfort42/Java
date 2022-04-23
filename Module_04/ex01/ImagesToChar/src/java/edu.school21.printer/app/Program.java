package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class Program {

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Number of arguments is incorrect");

        char whiteColor = args[0].charAt(0);
        char blackColor = args[1].charAt(0);

        List<String> arrayFromFile = Logic.readImage("/resources/it.bmp", blackColor, whiteColor);

        for (String line : arrayFromFile)
            System.out.println(line);

    }
}