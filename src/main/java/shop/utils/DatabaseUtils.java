package shop.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import shop.model.BuyItem;
import shop.model.Cart;
import shop.model.products.Console;
import shop.model.products.Laptop;
import shop.model.products.Phone;
import shop.model.products.Product;
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
            configuration.addAnnotatedClass(Phone.class);
            configuration.addAnnotatedClass(Laptop.class);
            configuration.addAnnotatedClass(Console.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    public static void shutDown() {
        getSessionFactory().close();
    }

    private static void createDBProductTables() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new Phone("Samsung Galaxy A51", 300, 64, "48mp", 4000));
        session.save(new Phone("Samsung Galaxy A51", 300, 64, "48mp", 4000));
        session.save(new Phone("Apple iPhone 12 Pro", 900, 512, "12mp", 3700));
        session.save(new Phone("Google pixel 5", 750, 256, "16mp", 4000));
        session.save(new Phone("Huawei P40", 670, 128, "50mp", 4200));
        session.save(new Laptop("Dell XPS 15", 1500, 15.6, "Intel Core i5", 256));
        session.save(new Laptop("Apple MacBook Air", 1200, 13.3, "Apple M1", 1000));
        session.save(new Laptop("Asus VivoBook S15", 800, 15.6, "Intel Core i5", 512));
        session.save(new Laptop("Acer Swift 3", 750, 14.0, "Amd Ryzer 7 4700U", 512));
        session.save(new Laptop("Lenovo ThinkPad X1", 1100, 15.6, "Intel Core i5", 1000));
        session.save(new Console("Playsation 4", 300, 500, "Black"));
        session.save(new Console("Playsation 5", 700, 500, "Black"));
        session.save(new Console("Xbox Series S", 370, 512, "White"));
        session.save(new Console("Xbox Series X", 850, 1000, "Black"));

        transaction.commit();
    }
}
