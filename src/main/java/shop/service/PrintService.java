package shop.service;

import org.hibernate.Session;
import shop.dao.CartDao;
import shop.model.Cart;
import shop.model.Product;
import shop.model.User;
import shop.utils.DatabaseUtils;

import javax.persistence.Query;
import java.util.List;

public class PrintService {

    public void printAllProducts() {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Product", Product.class);
        List<Product> products = query.getResultList();
        products.forEach(System.out::println);
    }

    public void printHistory(User user) {
        CartDao cartDao = new CartDao();
        List<Cart> carts = cartDao.getCarts(user.getUserId());
        carts.forEach(cart -> cart.printCartSummary());
    }

}
