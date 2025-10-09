package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.models.Product;
import dev.tanay.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    List<GenericProductDto> getAllProducts(){

    }
    @Override
    GenericProductDto getProductById(Long id) throws NotFoundException{

    }
    @Override
    GenericProductDto createProduct(GenericProductDto product){

    }
    @Override
    GenericProductDto deleteProduct(Long id){

    }
    @Override
    GenericProductDto updateProduct(GenericProductDto product, Long id){

    }
}
