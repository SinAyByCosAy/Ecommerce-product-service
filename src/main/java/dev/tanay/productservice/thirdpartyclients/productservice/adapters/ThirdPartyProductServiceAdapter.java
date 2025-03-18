package dev.tanay.productservice.thirdpartyclients.productservice.adapters;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceAdapter {
    List<GenericProductDto> getAllProducts();
    GenericProductDto getProductById(Long id)throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto deleteProduct(Long id);
    GenericProductDto updateProduct(GenericProductDto product, Long id);
}
