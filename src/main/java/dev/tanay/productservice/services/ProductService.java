package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.models.Product;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
}
