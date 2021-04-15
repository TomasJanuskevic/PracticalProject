package shop.model.products;

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
        @AttributeOverride(name="productId", column = @Column(name = "productId")),
        @AttributeOverride(name="brand", column = @Column(name = "brand")),
        @AttributeOverride(name="price", column = @Column(name = "price"))
})
public class Console extends Product{

int memory;
String color;

    public Console(String brand, int price, int memory, String color) {
        super(brand, price);
        this.memory = memory;
        this.color = color;
    }
}
