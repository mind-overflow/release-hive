package wtf.beatrice.releasehive.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.db.HibernateManager;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.util.JsonUtil;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public String registerUser(User user) {

        if(null == user.getUsername() || user.getUsername().isEmpty()) {
            return JsonUtil.spawnJsonError("Cannot register user without username");
        }


        if(null == user.getPassword() || user.getPassword().isEmpty()) {
            return JsonUtil.spawnJsonError("Cannot register user without password");
        }

        Session session = HibernateManager.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();

        return JsonUtil.convertToJson(user);
    }
}
