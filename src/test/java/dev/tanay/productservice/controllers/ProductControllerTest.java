package dev.tanay.productservice.controllers;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.services.ProductService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean(name = "FakeStoreProductService")
    private ProductService productService;
    @Nested
    class GetProducts{
        @Test //happy path
        void testGetAllProducts_ReturnSuccess()throws Exception{
            GenericProductDto product1 = getProduct(1L, "Iphone 17", "Phone", "Best Phone in town", 70000, "https://apple.com" );
            GenericProductDto product2 = getProduct(2L, "S25 Ultra", "Phone", "Good phone", 80000, "https://samsung.com");
            List<GenericProductDto> products = Arrays.asList(product1, product2);
            when(productService.getAllProducts())
                    .thenReturn(products);

            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].title", is("Iphone 17")))
                    .andExpect(jsonPath("$[1].category", is("Phone")))
                    .andExpect(jsonPath("$[1].price", is(80000.0)));

        }
        @Test//Empty Path
        void testGetAllProducts_ReturnEmptyList()throws Exception{
            when(productService.getAllProducts())
                    .thenReturn(Collections.emptyList());
            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(0)))
                    .andExpect(content().json("[]"));
        }
        GenericProductDto getProduct(Long id, String title, String category, String desc, double price, String image){
            GenericProductDto product = new GenericProductDto();
            product.setId(id);
            product.setTitle(title);
            product.setCategory(category);
            product.setDescription(desc);
            product.setPrice(price);
            product.setImage(image);
            return product;
        }
        @Test//Unhappy Path
        void testGetAllProducts_ServiceThrowsError()throws Exception{
            when(productService.getAllProducts())
                    .thenThrow(new RuntimeException("DB connection failed"));
            mockMvc.perform(get("/products"))
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.message", is("DB connection failed")));
        }
    }
    @Nested
    class GetProductsById{
        @Test
        void testGetProductById_ReturnSuccess()throws Exception {
            GenericProductDto product = new GenericProductDto();
            product.setId(10L);
            product.setTitle("Wilson Pro staff");
            when(productService.getProductById(10L))
                    .thenReturn(product);
            mockMvc.perform(get("/products/10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(10)))
                    .andExpect(jsonPath("$.title", is("Wilson Pro staff")));
        }
    }
}