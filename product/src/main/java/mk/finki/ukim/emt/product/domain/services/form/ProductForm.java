package mk.finki.ukim.emt.product.domain.services.form;

import lombok.Data;
import mk.finki.ukim.emt.product.domain.valueobjects.Quantity;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

@Data
public class ProductForm {
    private String productName;
    private Money cena;
    private int sales;

}
