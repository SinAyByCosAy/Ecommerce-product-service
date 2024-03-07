package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return new GenericProductDto();
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) {
        return null;
    }
}
