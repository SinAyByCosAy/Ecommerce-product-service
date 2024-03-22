package dev.tanay.productservice.thirdpartyclients.productservice;

import dev.tanay.productservice.dtos.FakeStoreProductDto;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductServiceClient {

    private RestTemplate restTemplate;
    private String baseProductRequestURL = "https://fakestoreapi.com/products";
    private String specificProductRequestURL = "https://fakestoreapi.com/products/{id}";
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

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
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

        if(response.getBody() == null){
            throw new NotFoundException("Product with id: "+id+" doesn't exist.");
        }
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
