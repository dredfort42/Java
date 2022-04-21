package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLs {

    public static void run(Path path) throws IOException {
        List<Path> content = Files.walk(path, 1).collect(Collectors.toList());
        for (Path item : content) {
            if (item != path)
                System.out.println(item.getFileName() + " " + Files.size(item) + " KB");
        }
    }

}
