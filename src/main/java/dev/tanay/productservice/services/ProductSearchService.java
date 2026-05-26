package dev.tanay.productservice.services;

import dev.tanay.productservice.models.ProductDocument;
import dev.tanay.productservice.repositories.ProductSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchService {
    private final ProductSearchRepository productSearchRepository;
    public ProductSearchService(ProductSearchRepository productSearchRepository){
        this.productSearchRepository = productSearchRepository;
    }
    public SearchPage<ProductDocument> searchProductDocument(String keyword, Pageable pageable){
        if(keyword == null || keyword.trim().isEmpty()) return null;
        return productSearchRepository.findByTitleMatchesOrDescriptionMatches(keyword, keyword, pageable);
    }
}
