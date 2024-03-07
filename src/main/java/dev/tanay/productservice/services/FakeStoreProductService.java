package dev.tanay.productservice.services;

import dev.tanay.productservice.dtos.FakeStoreProductDto;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.models.Product;
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
    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestURL = "https://fakestoreapi.com/products";
    private String getAllProductsURL = "https://fakestoreapi.com/products";
    private String updateProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestURL = "https://fakestoreapi.com/products/{id}";
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
                createProductRequestURL,
                HttpMethod.POST,
                requestEntity,
                FakeStoreProductDto.class
        );
        return mapToGenericDto(response.getBody());
    }

    @Override
    public GenericProductDto getProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(getProductRequestURL, FakeStoreProductDto.class, id);
        GenericProductDto product = new GenericProductDto();

        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                getAllProductsURL,
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
                updateProductRequestURL,
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
                deleteProductRequestURL,
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
