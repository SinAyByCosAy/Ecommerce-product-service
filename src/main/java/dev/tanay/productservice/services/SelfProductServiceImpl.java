package dev.tanay.productservice.services;

import dev.tanay.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public Product getProductById(Long id) {
        return null;
    }
}
