package dev.tanay.productservice;

import dev.tanay.productservice.models.Category;
import dev.tanay.productservice.models.Price;
import dev.tanay.productservice.models.Product;
import dev.tanay.productservice.repositories.CategoryRepository;
import dev.tanay.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepository productRepository, CategoryRepository categoryRepository){
        return args -> {
            //to save a new product
//            Product product = new Product();
//            product.setTitle("book2");
//            product.setImage("https://kk.com/boop.png");
//            product.setDescription("lengthy book");
//            Category category = new Category();
//            category.setName("Books");
//            product.setCategory(category);
//            Price price = new Price();
//            price.setCurrency("ruppee");
//            price.setPrice(22.2);
//            product.setPrice(price);
//            productRepository.save(product);

            //replace the UUID with the one in the DB
            Category category1 = categoryRepository.findById(UUID.fromString("125b5f4f-8c12-4bb7-80ce-b208b924b24e")).get();
            System.out.println(category1.getName());
            for(Product prod : category1.getProducts()) System.out.println(prod.getTitle());

            String title = "book1";
            System.out.println(productRepository.findByTitle(title).getTitle());

            String title2 = "book2";
            double price = 22.2;
            System.out.println(productRepository.findByTitleAndPrice_Price(title2, price).getDescription());

            List<Product> products = productRepository.findAllByTitle("book2");
            for(Product p : products) System.out.println("native find : " + p.getImage());
        };
    }
}
