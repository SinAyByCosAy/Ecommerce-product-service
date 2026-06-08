package dev.tanay.productservice.services;

import dev.tanay.productservice.thirdpartyclients.adapters.ThirdPartyProductServiceAdapter;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class FakeStoreProductService implements ProductService{
    private ThirdPartyProductServiceAdapter thirdPartyProductServiceAdapter;
    private RedisTemplate<String, Object> redisTemplate;
    public FakeStoreProductService(ThirdPartyProductServiceAdapter thirdPartyProductServiceAdapter,
                                   RedisTemplate redisTemplate){
        this.thirdPartyProductServiceAdapter = thirdPartyProductServiceAdapter;
        this.redisTemplate = redisTemplate;

    }
    @Override
    public List<GenericProductDto> getAllProducts(){
        System.out.println("got in: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return thirdPartyProductServiceAdapter.getAllProducts();
    }

    @Override
    public GenericProductDto getProductById(Long id){
        String key = "product:"+id;
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)) {
            System.out.println("Found product id: "+id+" in cache");
            return (GenericProductDto) ops.get(key);
        }
        GenericProductDto getProduct = thirdPartyProductServiceAdapter.getProductById(id);
        ops.set(key, getProduct);
        System.out.println("Got product id: "+id+" from source");
        return getProduct;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return thirdPartyProductServiceAdapter.createProduct(product);
    }

    @Override
    public GenericProductDto deleteProduct(Long id){
        return thirdPartyProductServiceAdapter.deleteProduct(id);
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto product, Long id){
        return thirdPartyProductServiceAdapter.updateProduct(product, id);
    }
}
