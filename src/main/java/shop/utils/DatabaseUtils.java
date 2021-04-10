package shop.utils;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import shop.model.BuyItem;
import shop.model.Cart;
import shop.model.Product;
import shop.model.User;

import java.util.Properties;

public class DatabaseUtils {
    private static SessionFactory sessionFactory;
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "mysqlpassword";

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();


            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/shop?serverTimezone=UTC");
            properties.put(Environment.USER, DB_USER);
            properties.put(Environment.PASS, DB_PASSWORD);
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "update"); //update, create, create-drop

            configuration.setProperties(properties);

            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Cart.class);
            configuration.addAnnotatedClass(BuyItem.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    public static void shutDown() {
        getSessionFactory().close();
    }
}
