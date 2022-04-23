package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import com.beust.jcommander.JCommander;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class Program {
    public static void main(String[] args) throws Exception {
        Args parameters = new Args();
        JCommander.newBuilder().addObject(parameters).build().parse(args);
        parameters.draw();
    }

}