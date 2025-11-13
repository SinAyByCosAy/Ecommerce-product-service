package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.models.Product;

import java.util.List;

public interface ProductService {

    List<GenericProductDto> getAllProducts();
    GenericProductDto getProductById(Long id)throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto deleteProduct(Long id)throws NotFoundException;
    GenericProductDto updateProduct(GenericProductDto product, Long id)throws NotFoundException;
}
