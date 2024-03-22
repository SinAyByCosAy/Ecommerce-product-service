package dev.tanay.productservice.thirdpartyclients.productservice;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto updateProductById(GenericProductDto product, Long id);
    GenericProductDto deleteProductById(Long id); 
}
