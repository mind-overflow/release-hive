package wtf.beatrice.releasehive.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.db.HibernateManager;
import wtf.beatrice.releasehive.model.User;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public void registerUser(User user) {
        Session session = HibernateManager.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }
}
