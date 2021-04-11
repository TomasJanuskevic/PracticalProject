package shop;

import shop.dao.CartDao;
import shop.dao.ProductDao;
import shop.model.Cart;
import shop.model.User;
import shop.service.ProductService;
import shop.service.ShopService;
import shop.service.UserService;
import shop.utils.DatabaseUtils;

import java.util.List;
import java.util.Scanner;


public class Application {

    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static ShopService shopService = new ShopService();

    public void application() {
        User user = shopService.connectingToShop();
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
                productService.addProductToCart(cart);
            } else if (userInput == 4) {
                System.out.println("Sorry this feature is in progress");
            } else if (userInput == 5) {
                shopService.confirmBuying(cart, user);
                System.out.println("Thank you for buying in our shop");
                return;
            } else if (userInput == 6) {
                DatabaseUtils.shutDown();
                System.out.println("Goodbye");
                return;
            } else if (userInput == 7) {
                CartDao cartDao = new CartDao();
                List<Cart> carts = cartDao.getCarts(user.getUserId());
                carts.forEach(cart1->cart1.printCartSummary());
            } else if (userInput == 8) {
                userService.deleteAccount(user);
                return;
            } else {
                System.out.println("Bad input, try again");
            }
        }
    }


}
