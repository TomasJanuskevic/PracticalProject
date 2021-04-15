package shop;

import shop.dao.ProductDao;
import shop.model.Cart;
import shop.model.User;
import shop.service.*;
import shop.utils.DatabaseUtils;

import java.util.Scanner;


public class Application {

    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static ShopService shopService = new ShopService();
    private static PrintService printService = new PrintService();
    private static CartService cartService = new CartService();

    public void application() {
        User user = shopService.connectingToShop();
        if (user == null) {
            return;
        }

        String meniu = "1. Show menu\n" +
                "2. Display products\n" +
                "3. Add product to your cart\n" +
                "4. Edit cart\n" +
                "5. Show cart\n" +
                "6. Confirm your buying\n" +
                "7. Cancel shopping\n" +
                "8. Shopping history\n" +
                "9. Delete Account";

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
                printService.printProducts();
            } else if (userInput == 3) {
                productService.addProductToCart(cart);
            } else if (userInput == 4) {
                cartService.editCart(cart);
            } else if (userInput == 5) {
                cart.printCartSummary();
            } else if (userInput == 6) {
                shopService.confirmBuying(cart, user);
                System.out.println("Thank you for buying in our shop");
                return;
            } else if (userInput == 7) {
                DatabaseUtils.shutDown();
                System.out.println("Goodbye");
                return;
            } else if (userInput == 8) {
                printService.printHistory(user);
            } else if (userInput == 9) {
                userService.deleteAccount(user);
                return;
            } else {
                System.out.println("Bad input, try again");
            }
        }
    }


}
