package shop.dao;

import org.hibernate.Session;
import shop.exception.FailedFindProductException;
import shop.utils.DatabaseUtils;
import shop.model.products.Product;

public class ProductDao {

    public Product getProduct(Long id) throws FailedFindProductException {
        Product product;
        Session session = DatabaseUtils.getSessionFactory().openSession();
        product = session.find(Product.class, id);
        if (product == null) {
            throw new FailedFindProductException();
        }
        return product;
    }


}
