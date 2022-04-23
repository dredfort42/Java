package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


import java.io.IOException;
import java.util.List;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = "--white")
    String firstColor;

    @Parameter(names = "--black")
    String secondColor;

    public void draw() throws Exception {
        System.out.println("--white: " + firstColor + " | --black: " + secondColor);

        List<String> arrayFromFile = Logic.readImage("/resources/it.bmp", ' ', '0');

        for (String line : arrayFromFile)
            System.out.println(line);
    }

}