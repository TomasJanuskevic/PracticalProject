package shop.service;

import org.hibernate.Session;
import shop.dao.CartDao;
import shop.dao.ProductDao;
import shop.exception.FailedFindProductException;
import shop.model.Cart;
import shop.model.products.Product;
import shop.model.User;
import shop.utils.DatabaseUtils;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class PrintService {

    public void printProducts() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("1. Phones\n" +
                "2. Laptops\n" +
                "3. Consoles\n" +
                "Select category -> ");
        int userInput = scanner.nextInt();

        if (userInput == 1) {
            printProductsFromCategory("from Phone");
            printProductDetailedDescription();
        } else if (userInput == 2) {
            printProductsFromCategory("from Laptop");
            printProductDetailedDescription();
        } else if (userInput == 3) {
            printProductsFromCategory("from Console");
            printProductDetailedDescription();
        } else {
            System.out.println("Bad input");
        }
    }

    private void printProductDetailedDescription() {
        System.out.print("For detailed description select product id -> ");
        Scanner scanner = new Scanner(System.in);
        Long productId = scanner.nextLong();
        ProductDao productDao = new ProductDao();
        try {
            Product product = productDao.getProduct(productId);
            product.productDescription();
        } catch (FailedFindProductException e) {
            e.printStackTrace();
        }


    }


    private void printProductsFromCategory(String categoryQuery) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Query query = session.createQuery(categoryQuery);
        List<Product> products = query.getResultList();
        products.forEach(System.out::println);
        session.close();
    }

    public void printHistory(User user) {
        CartDao cartDao = new CartDao();
        List<Cart> carts = cartDao.getCarts(user.getUserId());
        carts.forEach(cart -> cart.printCartSummary());
    }

    public void printMenu() {
        System.out.println("1. Show menu\n" +
                "2. Display products\n" +
                "3. Add product to your cart\n" +
                "4. Edit cart\n" +
                "5. Show cart\n" +
                "6. Confirm your buying\n" +
                "7. Cancel shopping\n" +
                "8. Shopping history\n" +
                "9. Delete Account");
    }

    public void printConnectingToShopMenu() {
        System.out.println("--------------------------");
        System.out.println("1. Log in\n" +
                "2. Create account\n" +
                "3. Exit");
        System.out.println("--------------------------");
        System.out.print("Select option -> ");
    }
}
