package shop.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.model.Cart;
import shop.model.User;
import shop.utils.DatabaseUtils;

import java.util.Scanner;

public class ShopService {

    private static UserService userService = new UserService();

    public User connectingToShop() {
        System.out.println("--------------------------");
        System.out.println("1. Log in\n" +
                "2. Create account");
        System.out.println("--------------------------");
        System.out.print("Select option -> ");

        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();

        if (userInput == 1) {
            return userService.logIn();
        } else if (userInput == 2) {
            return userService.createAccount();
        } else {
            System.out.println("Wrong input.");
        }
        return null;
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
