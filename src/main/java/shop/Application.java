package shop;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.dao.ProductDao;
import shop.dao.UserDao;
import shop.model.BuyItem;
import shop.model.Cart;
import shop.model.Product;
import shop.model.User;
import shop.utils.DatabaseUtils;

import java.util.Scanner;


public class Application {
    public void application() {
        User user = connectingToShop();
        if (user == null) {
            return;
        }

        String meniu = "1. Show menu\n" +
                "2. Display all products\n" +
                "3. Add product to your cart\n" +
                "4. Edit cart\n" +
                "5. Confirm your buying\n" +
                "6. Cancel shopping\n" +
                "7. Shopping history\n" +
                "8. Delete Account";

        System.out.println("Welcome to our shop");
        System.out.println("--------------------------");
        Cart cart = new Cart(user);
        System.out.println(meniu);

        while (true) {
            System.out.println();
            System.out.println("--------------------------");
            System.out.print("Select option -> ");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                System.out.println(meniu);
            } else if (userInput == 2) {
                ProductDao productDao = new ProductDao();
                productDao.printAllProducts();
            } else if (userInput == 3) {
                addProductToCart(cart);
            } else if (userInput == 4) {
                System.out.println("Sorry this feature is in progress");
            } else if (userInput == 5) {
                confirmBuying(cart, user);
                System.out.println("Thank you for buying in our shop");
                return;
            } else if (userInput == 6) {
                DatabaseUtils.shutDown();
                System.out.println("Goodbye");
                return;
            } else if (userInput == 7) {
                printBuyingHistory(user);
            } else if (userInput == 8) {
                deleteAccount(user);
                return;
            } else {
                System.out.println("Bad input, try again");
            }
        }
    }

    private void deleteAccount(User user) {
        UserDao userDao = new UserDao();
        userDao.deleteUser(user);
        DatabaseUtils.shutDown();
        System.out.println("Goodbye");
    }

    private void printBuyingHistory(User user) {

        user.getCarts().forEach(userCart -> userCart.printCartSummary());
    }

    private User connectingToShop() {
        System.out.println("--------------------------");
        System.out.println("1. Log in\n" +
                "2. Create account");
        System.out.println("--------------------------");
        System.out.print("Select option -> ");

        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();

        if (userInput == 1) {
            return logIn();
        } else if (userInput == 2) {
            return createAccount();
        } else {
            System.out.println("Wrong input.");
        }
        return null;
    }

    private User createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name -> ");
        String userName = scanner.nextLine();
        System.out.print("Enter password -> ");
        String userPassword = scanner.nextLine();

        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(userName, userPassword));
        transaction.commit();
        session.close();
        System.out.println("New account was created");
        UserDao userDao = new UserDao();
        return userDao.getUser(userName);
    }

    private User logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name -> ");
        String userName = scanner.nextLine();
        UserDao userDao = new UserDao();
        User userFromDB = userDao.getUser(userName);
        System.out.print("Enter password -> ");
        String userPassword = scanner.nextLine();

        if (userPassword.equals(userFromDB.getPassword())) {
            System.out.println("Welcome " + userFromDB.getName());
            return userFromDB;
        } else {
            System.out.println("Wrong password");
        }
        return null;
    }

    private void addProductToCart(Cart cart) {
        Scanner scanner = new Scanner(System.in);
        ProductDao productDao = new ProductDao();
        System.out.print("Select product id ->");
        Long selectedProductId = scanner.nextLong();
        Product product = productDao.getProduct(selectedProductId);
        System.out.println(product);
        System.out.print("Write quantity ->");
        int productQuantity = scanner.nextInt();
        BuyItem buyItem = new BuyItem(productQuantity, product, cart);
        cart.addBuyItem(buyItem);
    }

    private void confirmBuying(Cart cart, User user) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        user.addCart(cart);

        transaction.commit();
        DatabaseUtils.shutDown();
    }


}
