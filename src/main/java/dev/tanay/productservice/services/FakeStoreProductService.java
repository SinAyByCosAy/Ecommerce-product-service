package dev.tanay.productservice.services;

import dev.tanay.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getProductById(Long id) {
        return new Product();
    }
}
