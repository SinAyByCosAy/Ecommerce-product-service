package dev.tanay.productservice;

import dev.tanay.productservice.controllers.ProductController;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.services.FakeStoreProductService;
import dev.tanay.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean(name = "FakeStoreProductService")
    private ProductService productService;

    @Test
    void testGetProductById_ReturnsNotFound()throws Exception{
        when(productService.getProductById(99L))
                .thenThrow(new NotFoundException("Nahi mila ye product"));

        MvcResult mvcResult = mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", is("NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("Nahi mila ye product")))
                .andReturn();


        Exception exception = mvcResult.getResolvedException();
        assertNotNull(exception, "Woah, there should have been an error thrown");
        assertEquals(NotFoundException.class, exception.getClass());
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
