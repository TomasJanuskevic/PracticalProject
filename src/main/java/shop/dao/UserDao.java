package shop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.exception.ExistingUserException;
import shop.exception.FailedFindUserException;
import shop.utils.DatabaseUtils;
import shop.model.User;

import javax.persistence.Query;
import java.util.List;

public class UserDao {

    public User getUser(String name) throws FailedFindUserException {
        User user;
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from User where name=:name", User.class);
        query.setParameter("name", name);
        List<User> users = query.getResultList();

        if (users.isEmpty()) {
            throw new FailedFindUserException(name);
        } else {
            user = users.get(0);
        }
        session.close();
        return user;
    }

    public void saveUser(User user) throws ExistingUserException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (checkIfuserExist(user.getName())) {
            throw new ExistingUserException(user.getName());
        } else {
            session.save(user);
            transaction.commit();
        }
        session.close();
    }

    public void deleteUser(User user) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.refresh(user);
        session.delete(user);
        transaction.commit();
    }

    private boolean checkIfuserExist(String name) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from User where name=:name", User.class);
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        session.close();
        return !users.isEmpty();

    }
}
