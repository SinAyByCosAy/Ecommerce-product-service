package dev.tanay.productservice.controllers;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//all APIs around products
//For spring to know that this class will have a lot of endpoints, we'll annotate it with REST Controller
//spring injects it's power through annotations
@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
    private ProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

//Setter Injection
//    @Autowired
//    void setProductService(ProductService productService){
//        this.productService = productService;
//    }

// localhost:8080//products/id
// localhost:8080//products/123

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto product, @PathVariable Long id){
        return productService.updateProductById(product, id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable Long id){
        return productService.deleteProductById(id);
    }

}
