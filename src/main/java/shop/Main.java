package shop;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.model.products.Console;
import shop.model.products.Laptop;
import shop.model.products.Phone;
import shop.utils.DatabaseUtils;

public class Main {


    public static void main(String[] args) {
        //createDBProductTables();

        Application application = new Application();
        application.application();

    }

    private static void createDBProductTables() {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Phone phone1 = new Phone("Samsung Galaxy A51", 300, 64, "48mp", 4000);
        Phone phone2 = new Phone("Xiaomi Redmi 9 Pro", 400, 128, "64mp", 5020);
        Phone phone3 = new Phone("Apple iPhone 12 Pro", 900, 512, "12mp", 3700);
        Phone phone4 = new Phone("Google pixel 5", 750, 256, "16mp", 4000);
        Phone phone5 = new Phone("Huawei P40", 670, 128, "50mp", 4200);

        Laptop laptop1 = new Laptop("Dell XPS 15", 1500, 15.6, "Intel Core i5", 256);
        Laptop laptop2 = new Laptop("Apple MacBook Air", 1200, 13.3, "Apple M1", 1000);
        Laptop laptop3 = new Laptop("Asus VivoBook S15", 800, 15.6, "Intel Core i5", 512);
        Laptop laptop4 = new Laptop("Acer Swift 3", 750, 14.0, "Amd Ryzer 7 4700U", 512);
        Laptop laptop5 = new Laptop("Lenovo ThinkPad X1", 1100, 15.6, "Intel Core i5", 1000);

        Console console1 = new Console("Playsation 4", 300, 500, "Black");
        Console console2 = new Console("Playsation 5", 700, 500, "Black");
        Console console3 = new Console("Xbox Series S", 370, 512, "White");
        Console console4 = new Console("Xbox Series X", 850, 1000, "Black");

        session.save(phone1);
        session.save(phone2);
        session.save(phone3);
        session.save(phone4);
        session.save(phone5);
        session.save(laptop1);
        session.save(laptop2);
        session.save(laptop3);
        session.save(laptop4);
        session.save(laptop5);
        session.save(console1);
        session.save(console2);
        session.save(console3);
        session.save(console4);


        transaction.commit();
    }
}

