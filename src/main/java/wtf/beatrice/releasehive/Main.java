package wtf.beatrice.releasehive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wtf.beatrice.releasehive.db.HibernateManager;
import wtf.beatrice.releasehive.model.User;

import java.util.List;

@SpringBootApplication
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Hello world!");

        LOGGER.info("Registering shutdown hooks");
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        LOGGER.info("Initializing database backend");
        HibernateManager.initialize();

        LOGGER.info("Initializing Spring Boot");
        SpringApplication.run(Main.class, args);

        LOGGER.info("Spring Boot & DB initialized!");

        Session session = HibernateManager.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).getResultList();
        transaction.commit();

        users.forEach(user -> LOGGER.info("ID: {}, Name: {}", user.getUuid(), user.getUsername()));

    }

    private static final Thread shutdownHook = new Thread(() -> {
        HibernateManager.shutdown();
        LOGGER.info("Shutting down!");
    });
}
