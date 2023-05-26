package mk.finki.ukim.emt.product.domain.services.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.product.domain.exceptioons.ProductNotFoundException;
import mk.finki.ukim.emt.product.domain.models.Product;
import mk.finki.ukim.emt.product.domain.models.ProductId;
import mk.finki.ukim.emt.product.domain.repository.ProductRepository;
import mk.finki.ukim.emt.product.domain.services.ProductService;
import mk.finki.ukim.emt.product.domain.services.form.ProductForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product findById(ProductId id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product createProduct(ProductForm form) {
        Product p = Product.build(form.getProductName(),form.getCena(),form.getSales());
        productRepository.save(p);
        return p;
    }

    @Override
    public Product orderItemCreated(ProductId productId, int kolicinaproiz) {
        Product p = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        p.addSales(kolicinaproiz);
        productRepository.saveAndFlush(p);

        return p;
    }

    @Override
    public Product orderItemRemove(ProductId productId, int kolicinaproiz) {
        Product p = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        p.removeSales(kolicinaproiz);
        productRepository.saveAndFlush(p);

        return p;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
