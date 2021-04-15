package shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<BuyItem> buyItems = new LinkedHashSet<>();

    public Cart(User user) {
        this.user = user;
    }

    public void addBuyItem(BuyItem buyItem) {
        buyItems.add(buyItem);
    }

    public void printCartSummary() {
        System.out.println("Cart " + cartId + ":");
        buyItems.forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("Total price: " + totalPrice() + " Eur");
        System.out.println("===============");
    }

    private int totalPrice() {
        return buyItems.stream().mapToInt(buyitem -> buyitem.buyItemPrice()).sum();
    }

}
