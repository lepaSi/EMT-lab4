package mk.finki.ukim.emt.ordermenagement.service.forms;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mk.finki.ukim.emt.ordermenagement.domain.valueobjects.Product;

@Data
public class OrderItemForm {

    @NotNull
    private Product product;

    @Min(1)
    private int kolicinapn = 1;
}
