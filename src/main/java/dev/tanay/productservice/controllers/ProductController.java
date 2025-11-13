package dev.tanay.productservice.controllers;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//all APIs around products
//For spring to know that this class will have a lot of endpoints, we'll annotate it with REST Controller
//spring injects it's power through annotations
@RestController
@RequestMapping("${app.base.url}")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable Long id)throws NotFoundException {
        return productService.getProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.NOT_FOUND
        );
    }
    @PutMapping("{id}")
    public GenericProductDto updateProduct(@RequestBody GenericProductDto product, @PathVariable Long id) throws NotFoundException{
        return productService.updateProduct(product, id);
    }
}