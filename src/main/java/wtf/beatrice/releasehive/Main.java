package wtf.beatrice.releasehive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Hello world!");

        LOGGER.info("Initializing Spring Boot");
        SpringApplication.run(Main.class, args);
        LOGGER.info("Spring Boot initialized!");
    }
}
