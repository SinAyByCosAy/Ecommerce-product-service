package dev.tanay.productservice;

import dev.tanay.productservice.services.SelfProductServiceImpl;
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
    CommandLineRunner run(SelfProductServiceImpl selfProductService){
        return args -> {
            selfProductService.getAllCategories();
        };
    }
}
