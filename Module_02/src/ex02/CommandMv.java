package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CommandMv {

    public static void run(Path path, String srcPath, String destination) throws IOException {
        Path src = Paths.get(path + "/" + srcPath).normalize();
        Path dst = Paths.get(path + "/" + destination).normalize();

        if (Files.isRegularFile(src)) {
            if (Files.isDirectory(dst))
                dst = Paths.get(dst + "/" + src.getFileName()).normalize();
            Files.move(src, dst, REPLACE_EXISTING);
        } else {
            System.out.println("Oops... File path error!");
        }
    }
}
