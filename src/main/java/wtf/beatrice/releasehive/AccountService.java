package wtf.beatrice.releasehive;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountService
{

    public void registerUser(User user) {
        Session session = HibernateManager.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }
}
