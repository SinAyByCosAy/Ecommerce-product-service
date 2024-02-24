package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);
}
