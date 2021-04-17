package shop.service;

import org.junit.jupiter.api.Test;
import shop.model.BuyItem;
import shop.model.Cart;
import shop.model.products.Laptop;
import shop.model.products.Phone;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class CartServiceTest {

    @Test
    void findBuyItemByProductId() {
        Cart cart = new Cart();
        Phone phone = new Phone(1L, "Samsung Galaxy A51", 300, 64, "48mp", 4000);
        Laptop laptop = new Laptop(2L, "Asus VivoBook S15", 800, 15.6, "Intel Core i5", 512);
        BuyItem buyItem = new BuyItem(2, phone, cart);
        BuyItem expectedBuyItem = new BuyItem(3, laptop, cart);
        Set<BuyItem> buyItems = new LinkedHashSet<>();
        buyItems.add(buyItem);
        buyItems.add(expectedBuyItem);

        CartService cartService = new CartService();
        BuyItem actualBuyItem = cartService.findBuyItemByProductId(buyItems, 2L);

        assertThat(expectedBuyItem).isEqualTo(actualBuyItem);

    }

    @Test
    void deleteProductById() {
        Cart cart = new Cart();
        Phone phone = new Phone(1L, "Samsung Galaxy A51", 300, 64, "48mp", 4000);
        Laptop laptop = new Laptop(2L, "Asus VivoBook S15", 800, 15.6, "Intel Core i5", 512);
        BuyItem buyItem1 = new BuyItem(2, phone, cart);
        BuyItem buyItem2 = new BuyItem(3, laptop, cart);

        Set<BuyItem> actualBuyItems = new LinkedHashSet<>();
        actualBuyItems.add(buyItem1);
        actualBuyItems.add(buyItem2);

        Set<BuyItem> expectedBuyItems = new LinkedHashSet<>();
        expectedBuyItems.add(buyItem1);

        CartService cartService = new CartService();
        cartService.deleteProductById(actualBuyItems, 2L);

        assertThat(expectedBuyItems).isEqualTo(actualBuyItems);

    }
}