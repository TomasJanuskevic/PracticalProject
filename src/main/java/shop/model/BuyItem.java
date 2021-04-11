package shop.model;




import javax.persistence.*;


@Entity

public class BuyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyItemId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public BuyItem(int quantity, Product product, Cart cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

    public BuyItem() {
    }

    public Long getBuyItemId() {
        return buyItemId;
    }

    public void setBuyItemId(Long buyItemId) {
        this.buyItemId = buyItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return product.getProductId() + ". " + product.getItem() + ": " + product.getPrice() + " Eur - " + quantity + " qty";
    }

    public int buyItemPrice() {
        return product.getPrice() * quantity;
    }
}
