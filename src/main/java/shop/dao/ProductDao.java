package shop.dao;

import org.hibernate.Session;
import shop.utils.DatabaseUtils;
import shop.model.Product;

import javax.persistence.Query;
import java.util.List;

public class ProductDao {

    public void printAllProducts() {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Product", Product.class);
        List<Product> products = query.getResultList();
        products.forEach(System.out::println);
    }

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
