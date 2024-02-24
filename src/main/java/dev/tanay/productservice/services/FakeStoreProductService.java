package dev.tanay.productservice.services;

import dev.tanay.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    @Override
    public String getProductById(Long id) {
        return "Fetched details for the product id: "+ id;
    }
}
