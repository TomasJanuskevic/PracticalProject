package shop.model.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "productId", column = @Column(name = "productId")),
        @AttributeOverride(name = "brand", column = @Column(name = "brand")),
        @AttributeOverride(name = "price", column = @Column(name = "price"))
})
public class Laptop extends Product {

    double displaySize;
    String cpu;
    int memory;

    public Laptop(String brand, int price, double displaySize, String cpu, int memory) {
        super(brand, price);
        this.displaySize = displaySize;
        this.cpu = cpu;
        this.memory = memory;
    }

    public Laptop(Long productId, String brand, int price, double displaySize, String cpu, int memory) {
        super(productId, brand, price);
        this.displaySize = displaySize;
        this.cpu = cpu;
        this.memory = memory;
    }

    @Override
    public void productDescription() {
        System.out.println(getBrand() + ":"
                + "\nDisplay size: " + displaySize
                + "\nCPU: " + cpu
                + "\nMemory: " + memory
                + "\nPrice: " + getPrice());
    }
}
