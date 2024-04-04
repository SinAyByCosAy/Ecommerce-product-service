package dev.tanay.productservice.thirdpartyclients.productservice.fakestore;

import dev.tanay.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//Wrapper over FakeStore API
@Service
public class FakeStoreProductServiceClient {

    private RestTemplate restTemplate;

    @Value("${fakestore.api.url}${fakestore.api.paths.products}")
    private String baseProductRequestURL;

    @Value("${fakestore.api.url}${fakestore.api.paths.products}/{id}")
    private String specificProductRequestURL;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
//        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
//                createProductRequestURL, product, GenericProductDto.class
//        );
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                baseProductRequestURL,
                HttpMethod.POST,
                requestEntity,
                FakeStoreProductDto.class
        );
        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> response =
//                restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDto.class, id);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                specificProductRequestURL,
                HttpMethod.GET,
                null,
                FakeStoreProductDto.class,
                id
        );
        return response.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                baseProductRequestURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
        );
        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(GenericProductDto product, Long id) {
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                specificProductRequestURL,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        return response.getBody();
    }
    
    public FakeStoreProductDto deleteProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> respone = restTemplate.exchange(
                specificProductRequestURL,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );
        return respone.getBody();
    }
}
