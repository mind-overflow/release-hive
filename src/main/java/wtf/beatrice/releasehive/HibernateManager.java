package wtf.beatrice.releasehive;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateManager
{
    private static HibernateManager instance;
    private static Session session;
    private static SessionFactory sessionFactory;


    private HibernateManager() {
    }

    public static void initialize() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static void shutdown() {
        if(session != null && !session.isOpen()) {
            session.close();
        }
        if(sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }


    public static Session getSession() {
         if(session != null && (!session.isOpen() || !session.isConnected())) {
             session.close();
         }

        session = sessionFactory.openSession();
        return session;
    }

    public static Transaction beginTransaction() {
        return getSession().beginTransaction();
    }
}
