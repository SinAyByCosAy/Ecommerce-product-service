package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.models.Product;
import dev.tanay.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<GenericProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return convertToGenericDto(products);
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        return new GenericProductDto();
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return new GenericProductDto();
    }
    @Override
    public GenericProductDto deleteProduct(Long id){
        return new GenericProductDto();
    }
    @Override
    public GenericProductDto updateProduct(GenericProductDto product, Long id){
        return new GenericProductDto();
    }
    private List<GenericProductDto> convertToGenericDto(List<Product> productList){
        return productList.stream()
                .map(this::mapToGenericDto)
                .collect(Collectors.toList());
    }
    private GenericProductDto mapToGenericDto(Product product){
        GenericProductDto genericProduct = new GenericProductDto();
        genericProduct.setId(product.getUuid().getMostSignificantBits()); //converting UUID to long for now due to the structure of GenericProductDto
        genericProduct.setTitle(product.getTitle());
        genericProduct.setPrice(product.getPrice().getPrice());
        genericProduct.setImage(product.getImage());
        genericProduct.setDescription(product.getDescription());
        genericProduct.setCategory(product.getCategory().getName());
        return genericProduct;
    }
}
