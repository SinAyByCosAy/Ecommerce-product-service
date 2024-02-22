package dev.tanay.productservice.controllers;

import org.springframework.web.bind.annotation.*;

//all APIs around products
//For spring to know that this class will have a lot of endpoints, we'll annotate it with REST Controller
//spring injects it's power through annotations
@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public String getAllProducts(){
        return "3 products: Iphone, MacBook Pro, Boat Speakers";
    }
    @GetMapping("`{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Got the product : "+ id;
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }

    @PostMapping
    public void createProduct(){

    }

    @PutMapping
    public void updateProductById(){

    }

}
