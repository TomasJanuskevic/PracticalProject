package shop.dao;

import org.hibernate.Session;
import shop.model.Cart;
import shop.utils.DatabaseUtils;

import javax.persistence.Query;
import java.util.List;

public class CartDao {
    public List<Cart> getCarts(Long userId) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Cart WHERE user_id=: userId");
        query.setParameter("userId", userId);
        return query.getResultList();

    }

}
