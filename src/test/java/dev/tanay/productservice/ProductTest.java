package dev.tanay.productservice;

import dev.tanay.productservice.controllers.ProductController;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.services.ProductService;
import dev.tanay.productservice.services.SelfProductServiceImpl;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    @Test
    @DisplayName("Boom Boom test")
    public void testRandom() {
        assert (2 == 1 + 1);
        assertEquals(11, 1 + 1, "it's not equal");
    }

    @Test
    public void testAdd() {
        assertTrue(-2 + 1 == 0, "negative and positive addition is not correct");
        assert 1 + 1 == 2;
    }

    @Test
    public void testNull() {
        assertNull(new Object(), "this is not null");
    }
//    @Test
//    public void testException(){
//        assertThrows(NotFoundException.class, () -> selfProductService.getProductById(28L));
//    }

    @Test
    public void returnsNullWhenProductDoesntExist() throws NotFoundException {
        GenericProductDto req = new GenericProductDto();
        when(productService.getProductById(any(Long.class)))
                .thenReturn(req);
        GenericProductDto product = productController.updateProduct(req, 50L);
        assertEquals(req, productController.getProductById(28L));
    }
}
