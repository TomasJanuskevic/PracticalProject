package shop.service;

import shop.model.BuyItem;
import shop.model.Cart;

import java.util.Scanner;
import java.util.Set;

public class CartService {
    public Cart editCart(Cart cart) {
        Scanner scanner = new Scanner(System.in);
        cart.getBuyItems().stream().forEach(System.out::println);

        System.out.print("Select product id -> ");
        Long productId = scanner.nextLong();

        BuyItem buyItem = findBuyItemByProductId(cart.getBuyItems(), productId);
        System.out.println(buyItem);

        System.out.println("Select option:\n" +
                           "1. Change quantity\n" +
                           "2. Delete product");
        int userInput = scanner.nextInt();

        if(userInput == 1){
            System.out.print("Write quantity -> ");
            int productQuantity = scanner.nextInt();
            buyItem.setQuantity(productQuantity);
        } else  if (userInput == 2){
            deleteProductById(cart.getBuyItems(), productId);
        }
        return cart;
    }

    public BuyItem findBuyItemByProductId(Set<BuyItem> buyItems, Long productId) {
        for (BuyItem buyItem : buyItems) {
            if (buyItem.getProduct().getProductId().equals(productId)) {
                return buyItem;
            }
        }
        System.out.println("Didn't find product");
        return null;
    }

    private void deleteProductById(Set<BuyItem> buyItems, Long productId){
        for (BuyItem buyItem : buyItems) {
            if(buyItem.getProduct().getProductId().equals(productId)){
                buyItems.remove(buyItem);
            }
        }
    }
}
