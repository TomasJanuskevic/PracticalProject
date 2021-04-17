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
public class Phone extends Product {

    int memory;
    String camera;
    int battery;

    public Phone(String brand, int price, int memory, String camera, int battery) {
        super(brand, price);
        this.memory = memory;
        this.camera = camera;
        this.battery = battery;
    }

    public Phone(Long productId, String brand, int price, int memory, String camera, int battery) {
        super(productId, brand, price);
        this.memory = memory;
        this.camera = camera;
        this.battery = battery;
    }

    @Override
    public void productDescription() {
        System.out.println(getBrand() + ":"
                + "\nMemory: " + memory
                + "\nCamera: " + camera
                + "\nBattery: " + battery
                + "\nPrice: " + getPrice());
    }
}
