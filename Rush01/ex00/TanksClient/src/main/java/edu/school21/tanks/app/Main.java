package edu.school21.tanks.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import edu.school21.tanks.client.Client;
import edu.school21.tanks.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--server-port")
    private Integer port;
    private static final String HOST = "localhost";
    private static final String HINT = "Usage: tanks-client [options]\n\t--server-port=[0-65535]\t\tspecify explicit port to connect";

    public static void main(String[] args) {
        Main main = new Main();

        try {
            JCommander.newBuilder().addObject(main).build().parse(args);
            main.parseArgs();
        } catch (ParameterException e) {
            System.err.println(HINT);
            System.exit(-1);
        }

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            context.getBean(Client.class).run(HOST, main.port);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void parseArgs() throws ParameterException {
        if (this.port == null) {
            this.port = 0;
        } else if (this.port < 0 || this.port > 65535) {
            throw new ParameterException("");
        }
    }
}
