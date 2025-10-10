package dev.tanay.productservice.repositories;

import dev.tanay.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitle(String title);
    Product findByTitleAndPrice_Price(String title, double price);
    @Query(CustomQueries.FIND_ALL_BY_TITLE)
    List<Product> findAllByTitle(String name);
    List<Product> findAll();
}
