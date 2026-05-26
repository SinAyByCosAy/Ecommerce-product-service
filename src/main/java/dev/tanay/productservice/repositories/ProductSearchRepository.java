package dev.tanay.productservice.repositories;

import dev.tanay.productservice.models.ProductDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, String> {
    SearchPage<ProductDocument> findByTitleMatchesOrDescriptionMatches(
            String titleKeyword,
            String descKeyword,
            Pageable pageable
    );
}
