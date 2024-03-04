package dev.tanay.productservice.controllers;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

//    @Autowired
//    void setProductService(ProductService productService){
//        this.productService = productService;
//    }
    @GetMapping
    public String getAllProducts(){
        return "3 products: Iphone, MacBook Pro, Boat Speakers";
    }

    // localhost:8080//products/id
    // localhost:8080//products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }

    @PostMapping
    public String createProduct(@RequestBody GenericProductDto Product){
        return Product.getTitle();
    }

    @PutMapping
    public void updateProductById(){

    }

}
