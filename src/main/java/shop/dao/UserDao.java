package shop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.utils.DatabaseUtils;
import shop.model.User;

import javax.persistence.Query;

public class UserDao {

    public User getUser(String name) {
        User user = null;
        try {
            Session session = DatabaseUtils.getSessionFactory().openSession();
            Query query = session.createQuery("from User where name=:name", User.class);
            query.setParameter("name", name);
            user = (User) query.getSingleResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(User user){
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.refresh(user);
        session.delete(user);
        transaction.commit();
    }
}
