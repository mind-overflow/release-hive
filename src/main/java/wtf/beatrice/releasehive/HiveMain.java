package wtf.beatrice.releasehive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wtf.beatrice.releasehive.db.HibernateManager;
import wtf.beatrice.releasehive.dtos.UserDto;
import wtf.beatrice.releasehive.models.User;

import java.util.List;

@SpringBootApplication
public class HiveMain {

    private static final Logger LOGGER = LogManager.getLogger(HiveMain.class);

    public static void main(String[] args) {

        LOGGER.info("Registering shutdown hooks");
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        LOGGER.info("Initializing database backend");
        HibernateManager.initialize();

        LOGGER.info("Initializing Spring Boot");
        SpringApplication.run(HiveMain.class, args);
        LOGGER.info("Spring Boot & DB initialized!");

        Session session = HibernateManager.getSession();
        Transaction transaction = session.beginTransaction();
        List<UserDto> users = session.createQuery("FROM User", UserDto.class).getResultList();
        transaction.commit();

        StringBuilder usersListBuilder = new StringBuilder("[");
        users.forEach(user -> usersListBuilder.append(user.getUsername()).append(","));
        usersListBuilder.deleteCharAt(usersListBuilder.length() - 1);
        usersListBuilder.append("]");

        LOGGER.info("Found users: {}", usersListBuilder);
        LOGGER.info("Total: {}", users.size());
}

    private static final Thread shutdownHook = new Thread(() -> {
        HibernateManager.shutdown();
        LOGGER.info("Shutting down!");
    });
}
