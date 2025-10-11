package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.models.Category;
import dev.tanay.productservice.models.Price;
import dev.tanay.productservice.models.Product;
import dev.tanay.productservice.repositories.CategoryRepository;
import dev.tanay.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<GenericProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return convertToGenericDto(products);
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id: "+id+" not present"));
        return mapToGenericDto(product);
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        Price price = new Price();
        price.setPrice(product.getPrice());
        newProduct.setPrice(price);
        Category category = categoryRepository.findByName(product.getCategory())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(product.getCategory());
                    return newCategory;
                });
        newProduct.setCategory(category);
        productRepository.save(newProduct); //new uuid will get generated here while inserting since it was null
        product.setId(newProduct.getId()); //in actual projects, we should create a separate response dto
        //it should be separate as we might not want all the data to be sent back
        return product;
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
        genericProduct.setId(product.getId()); //converting UUID to long for now due to the structure of GenericProductDto
        genericProduct.setTitle(product.getTitle());
        genericProduct.setPrice(product.getPrice().getPrice());
        genericProduct.setImage(product.getImage());
        genericProduct.setDescription(product.getDescription());
        genericProduct.setCategory(product.getCategory().getName());
        return genericProduct;
    }
}
