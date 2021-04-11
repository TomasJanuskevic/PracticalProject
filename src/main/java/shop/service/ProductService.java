package shop.service;

import shop.dao.ProductDao;
import shop.model.BuyItem;
import shop.model.Cart;
import shop.model.Product;

import java.util.Scanner;

public class ProductService {
    public void addProductToCart(Cart cart) {
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
}
