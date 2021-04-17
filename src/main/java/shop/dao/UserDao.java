package shop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.exception.ExistingUserException;
import shop.exception.FailedFindUserException;
import shop.utils.DatabaseUtils;
import shop.model.User;

import javax.persistence.Query;

public class UserDao {

    public User getUser(String name) throws FailedFindUserException {
        User user;
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from User where name=:name", User.class);
        query.setParameter("name", name);

        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            throw new FailedFindUserException(name);
        }

        session.close();
        return user;
    }

    public void saveUser(User user) throws ExistingUserException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            throw new ExistingUserException(user.getName());
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
}
