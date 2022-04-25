package Game;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class MyArgsParser {
    public MyArgsParser(String[] args) {
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(args);
    }

    @Parameter(names="--enemiesCount")
    public int enemiesCount;
    @Parameter(names="--wallsCount")
    public int wallsCount;
    @Parameter(names="--size")
    public int size;
    @Parameter(names="--profile")
    public String profile;
}
