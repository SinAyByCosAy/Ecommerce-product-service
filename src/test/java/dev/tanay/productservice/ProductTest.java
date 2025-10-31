package dev.tanay.productservice;

import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.services.SelfProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {
    @Autowired
    private SelfProductServiceImpl selfProductService;
    @Test
    @DisplayName("Boom Boom test")
    public void testRandom(){
        assert(2 == 1+1);
        assertEquals(11, 1 + 1, "it's not equal");
    }
    @Test
    public void testAdd(){
        assertTrue(-2 + 1 == 0, "negative and positive addition is not correct" );
        assert 1 + 1 == 2;
    }
    @Test
    public void testNull(){
        assertNull(new Object(), "this is not null");
    }
    @Test
    public void testException(){
        assertThrows(NotFoundException.class, () -> selfProductService.getProductById(28L));
    }
}
