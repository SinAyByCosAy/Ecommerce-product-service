package dev.tanay.productservice.controllers;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.dtos.SearchRequestDto;
import dev.tanay.productservice.services.SelfProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this is a class for manually creating the Pageable object
@RestController
@RequestMapping("/search")
public class SearchController {
    private SelfProductServiceImpl productService;
    public SearchController(SelfProductServiceImpl productService){
        this.productService = productService;
    }
    @PostMapping
    public Page<GenericProductDto> searchProductManually(@RequestBody SearchRequestDto searchRequestDto){
        return productService.searchProductsManually(searchRequestDto);
    }
}
