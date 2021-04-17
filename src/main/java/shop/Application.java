package shop;

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
            DatabaseUtils.shutDown();
            System.out.println("Goodbye");
            return;
        }

        System.out.println("Welcome to our shop");
        System.out.println("--------------------------");
        Cart cart = new Cart(user);
        printService.printMenu();

        while (true) {
            System.out.println();
            System.out.println("--------------------------");
            System.out.print("Select option -> ");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                printService.printMenu();
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
