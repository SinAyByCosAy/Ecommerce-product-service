package dev.tanay.productservice.services;

import dev.tanay.productservice.models.Product;

public interface ProductService {

    Product getProductById(Long id);
}
