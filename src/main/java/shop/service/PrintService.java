package shop.service;

import org.hibernate.Session;
import shop.dao.CartDao;
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
        System.out.println("Select category:\n" +
                "1. Phones\n" +
                "2. Laptops\n" +
                "3. Consoles");
        int userInput = scanner.nextInt();

        if (userInput == 1){
            printProductsFromCategory("from Phone");
        } else if(userInput == 2){
            printProductsFromCategory("from Laptop");
        } else if(userInput == 3){
            printProductsFromCategory("from Console");
        } else {
            System.out.println("Bad input");
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

}
