package dev.tanay.productservice;

import dev.tanay.productservice.models.Category;
import dev.tanay.productservice.models.Product;
import dev.tanay.productservice.repositories.CategoryRepository;
import dev.tanay.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepository productRepository, CategoryRepository categoryRepository){
        return args -> {
            Product product = new Product();
            product.setTitle("book1");
            product.setImage("https://kk.com/abc.png");
            product.setDescription("Great book");
            Category category = new Category();
            category.setName("Books");
            product.setCategory(category);
            productRepository.save(product);


            Category category1 = categoryRepository.findById(UUID.fromString("c078b998-fcbf-4976-8b24-665ba313a075")).get();
            System.out.println(category1.getName());
            for(Product prod : category1.getProducts()) System.out.println(prod.getTitle());
        };
    }
}
