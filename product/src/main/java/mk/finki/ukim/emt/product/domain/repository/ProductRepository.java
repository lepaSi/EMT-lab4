package mk.finki.ukim.emt.product.domain.repository;

import mk.finki.ukim.emt.product.domain.models.Product;
import mk.finki.ukim.emt.product.domain.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {

}
