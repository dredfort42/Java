package ex02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandCd {

    public static Path run(Path path, String goToFolder) {
        Path destinationFolder = Paths.get( path + "/" + goToFolder).normalize();
        if (Files.exists(destinationFolder) && Files.isDirectory(destinationFolder)) {
            System.out.println(destinationFolder);
            return destinationFolder;
        } else {
            System.out.println("[Incorrect path] " + destinationFolder);
            return path;
        }
    }

}
