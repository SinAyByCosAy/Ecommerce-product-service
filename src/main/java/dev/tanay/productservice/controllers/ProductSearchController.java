package dev.tanay.productservice.controllers;

import dev.tanay.productservice.models.ProductDocument;
import dev.tanay.productservice.services.ProductSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
public class ProductSearchController {
    private final ProductSearchService productSearchService;
    public ProductSearchController(ProductSearchService productSearchService){
        this.productSearchService = productSearchService;
    }
    @GetMapping("/search")
    public Page<ProductDocument> searchProductDocument(
            @RequestParam(name = "q", defaultValue = "") String keyword,
            Pageable pageable){
        return productSearchService.searchProductDocument(keyword, pageable);
    }
}
