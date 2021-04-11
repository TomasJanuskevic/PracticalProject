package shop.dao;

import org.hibernate.Session;
import shop.utils.DatabaseUtils;
import shop.model.Product;

public class ProductDao {

    public Product getProduct(Long id) {
        Product product = null;
        try {
            Session session = DatabaseUtils.getSessionFactory().openSession();
            product = session.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }



}
