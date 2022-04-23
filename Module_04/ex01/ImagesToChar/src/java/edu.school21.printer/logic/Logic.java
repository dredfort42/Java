package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static List<String> readImage(String file, char blackColor, char whiteColor) throws IOException {

        BufferedImage image = ImageIO.read(Logic.class.getResource(file));
        List<String> tmp = new ArrayList<>(image.getHeight() * image.getWidth());

        for (int y = 0; y < image.getHeight(); y++) {
            String line = "";
            for (int x = 0; x < image.getWidth(); x++) {
                int pixelColor = image.getRGB(x, y);
                if (pixelColor == Color.black.getRGB())
                    line += blackColor;
                else if (pixelColor == Color.white.getRGB())
                    line += whiteColor;
                else
                    line += '⌖';
            }
            tmp.add(line);
        }

        return tmp;

    }
}