package dev.tanay.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.services.ProductService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean(name = "FakeStoreProductService")
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
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
    class GetProductById{
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
        @Test
        void testGetProductById_NotFound()throws Exception{
            when(productService.getProductById(12L))
                    .thenThrow(new NotFoundException("Product not found"));
            mockMvc.perform(get("/products/12"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.errorCode", is("NOT_FOUND")))
                    .andExpect(jsonPath("$.message", is("Product not found")));
        }
    }
    @Nested
    class CreateProduct{
        @Test//happy case
        void testCreateProduct_ReturnSuccess() throws Exception{
            GenericProductDto req = new GenericProductDto();
            req.setTitle("Boom shankar");
            req.setCategory("Speakers");
            GenericProductDto res = new GenericProductDto();
            res.setId(1L);
            res.setTitle("Boom shankar");
            res.setCategory("Speakers");
            when(productService.createProduct(any()))
                    .thenReturn(res);
            mockMvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(req))
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(res)));
        }
        //unhappy case would be: unable to create product due to DB/API connection failure
        //which should result in a runtime exception like above
    }
    @Nested
    class UpdateProduct{
        @Test//happy case
        void testUpdateProduct_ReturnSuccess() throws Exception{
            GenericProductDto product = new GenericProductDto();
            product.setTitle("New product");
            when(productService.updateProduct(any(GenericProductDto.class), any()))
                    .thenReturn(product);
            mockMvc.perform(put("/products/69")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(product)))
                    .andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(product))); //better to use jsonpath matcher to compare specified fields
        }
        @Test//unhappy case
        void testUpdateProduct_NotFound() throws Exception{
            GenericProductDto product = new GenericProductDto();
            product.setTitle("New Product");
            when(productService.updateProduct(any(GenericProductDto.class), any()))
                    .thenThrow(new NotFoundException("Product not found"));
            mockMvc.perform(put("/products/69")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(product)))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.errorCode", is("NOT_FOUND")))
                    .andExpect(jsonPath("$.message", is("Product not found")));
        }
    }
    @Nested
    class DeleteProduct{
        @Test//happy
        void testDeleteProduct_ReturnSuccess() throws Exception{
            GenericProductDto product = new GenericProductDto();
            product.setId(69L);
            product.setTitle("Books");
            when(productService.deleteProduct(any(Long.class)))
                    .thenReturn(product);
            mockMvc.perform(delete("/products/69"))
                    .andExpect(status().isNotFound())//getting back a response entity with status set as NOT FOUND
                    .andExpect(jsonPath("$.title", is("Books")));
        }
        @Test//unhappy
        void testDeleteProduct_NotFound() throws Exception{
            when(productService.deleteProduct(any(Long.class)))
                    .thenThrow(new NotFoundException("Product not found to be deleted"));
            mockMvc.perform(delete("/products/69"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.errorCode", is("NOT_FOUND")))
                    .andExpect(jsonPath("$.message", is("Product not found to be deleted")));
        }
    }
}

//More tests that can be performed
//
//Validation Errors (400 Bad Request):
//
//Action:
//Add validation annotations (like @NotBlank) to your GenericProductDto fields (e.g., title).
//Add @Valid to your controller's createProduct method:
//public GenericProductDto createProduct(@Valid @RequestBody GenericProductDto product)
//
//Test:
//Write a test that POSTs a product with a null or empty title.
//
//Assert: mockMvc.perform(...) .andExpect(status().isBadRequest());
//
//Malformed JSON (400 Bad Request):
//
//Action:
//No code change needed. Spring Boot does this for you.
//
//Test: Send a request with invalid JSON syntax.
//
//Assert: mockMvc.perform(post("/products")
//    .contentType(MediaType.APPLICATION_JSON)
//    .content("{\"title\": \"test\"")) (note the missing }))
//        .andExpect(status().isBadRequest());