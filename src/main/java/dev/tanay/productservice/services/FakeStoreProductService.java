package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.FakeStoreProductDto;
import dev.tanay.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private String baseProductRequestURL = "https://fakestoreapi.com/products";
    private String specificProductRequestURL = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
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
        return mapToGenericDto(response.getBody());
    }

    @Override
    public GenericProductDto getProductById(Long id) {
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
        return mapToGenericDto(response.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                baseProductRequestURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
        );
        List<FakeStoreProductDto> fakeStoreProducts = response.getBody();
        List<GenericProductDto> products = convertToGenericDto(fakeStoreProducts);
        return products;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) {
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                specificProductRequestURL,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        return mapToGenericDto(response.getBody());
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> respone = restTemplate.exchange(
                specificProductRequestURL,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );
        return mapToGenericDto(respone.getBody());
    }

    private List<GenericProductDto> convertToGenericDto(List<FakeStoreProductDto> fakeStoreProducts){
        return fakeStoreProducts.stream()
                .map(this::mapToGenericDto)
                .collect(Collectors.toList());
    }
    private GenericProductDto mapToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());

        return product;
    }
}
