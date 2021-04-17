package shop.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.model.Cart;
import shop.model.User;
import shop.utils.DatabaseUtils;
import java.util.Scanner;

public class ShopService {

    private static UserService userService = new UserService();
    private static PrintService printService = new PrintService();

    public User connectingToShop() {
        while (true) {
            printService.printConnectingToShopMenu();
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                User user = userService.logIn();
                if (user != null) {
                    return user;
                }
            } else if (userInput == 2) {
                User user = userService.createAccount();
                if (user != null) {
                    return user;
                }
            } else if (userInput == 3) {
                return null;
            } else {
                System.out.println("Wrong input.");
            }
        }
    }

    public void confirmBuying(Cart cart, User user) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);
        user.addCart(cart);

        transaction.commit();
        DatabaseUtils.shutDown();
    }
}
