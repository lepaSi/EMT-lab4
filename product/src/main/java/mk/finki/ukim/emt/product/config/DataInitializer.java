package mk.finki.ukim.emt.product.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.product.domain.models.Product;
import mk.finki.ukim.emt.product.domain.repository.ProductRepository;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;

    @PostConstruct
    public void initData(){
        Product p1 = Product.build("Analgin", Money.valueOd(Currency.MKD,500.0),10);
        Product p2 = Product.build("Paracetamol", Money.valueOd(Currency.MKD,150.0),100);
        if (productRepository.findAll().isEmpty()){
            productRepository.saveAll(Arrays.asList(p1,p2));
        }

    }
}
