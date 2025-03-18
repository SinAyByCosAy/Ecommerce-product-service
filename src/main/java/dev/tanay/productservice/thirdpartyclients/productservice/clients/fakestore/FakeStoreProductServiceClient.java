package dev.tanay.productservice.thirdpartyclients.productservice.clients.fakestore;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.thirdpartyclients.productservice.dtos.FakeStoreProductDto;
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

//    private String baseProductRequestURL = "https://fakestoreapi.com/products";
//    private String getProductRequestURL = baseProductRequestURL + "/{id}";

    @Value("${fakestore.api.url}/${fakestore.api.paths.products}")
    private String baseProductRequestURL;

    @Value("${fakestore.api.url}/${fakestore.api.paths.products}/{id}")
    private String getProductRequestURL;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    public List<FakeStoreProductDto> getAllProducts(){
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                baseProductRequestURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>(){}
        );

        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id)throws NotFoundException {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                getProductRequestURL,
                HttpMethod.GET,
                null,
                FakeStoreProductDto.class,
                id
        );
        if(response.getBody() == null) throw new NotFoundException("Product with id: "+id+" doesn't exist");
        return response.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                baseProductRequestURL,
                HttpMethod.POST,
                requestEntity,
                FakeStoreProductDto.class
        );
        return response.getBody();
    }

    public FakeStoreProductDto deleteProduct(Long id){
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                getProductRequestURL,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );
        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(GenericProductDto product, Long id){
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                getProductRequestURL,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );

        return response.getBody();
    }
}