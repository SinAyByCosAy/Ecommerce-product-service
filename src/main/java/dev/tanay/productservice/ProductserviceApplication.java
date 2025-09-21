package dev.tanay.productservice;

import dev.tanay.productservice.models.Category;
import dev.tanay.productservice.models.Product;
import dev.tanay.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepository productRepository){
        return args -> {
            Product product = new Product();
            product.setTitle("book1");
            product.setImage("https://kk.com/abc.png");
            product.setDescription("Great book");
            Category category = new Category();
            category.setName("Books");
            product.setCategory(category);
            productRepository.save(product);
        };
    }
}
