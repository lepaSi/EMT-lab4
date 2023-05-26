package mk.finki.ukim.emt.product.domain.services;

import mk.finki.ukim.emt.product.domain.models.Product;
import mk.finki.ukim.emt.product.domain.models.ProductId;
import mk.finki.ukim.emt.product.domain.services.form.ProductForm;

import java.util.List;

public interface ProductService {
    Product findById(ProductId id);
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId,int kolicinaproiz);
    Product orderItemRemove(ProductId productId,int kolicinaproiz);

    List<Product> getAll();
}
