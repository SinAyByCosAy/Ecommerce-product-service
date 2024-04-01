package dev.tanay.productservice.services;

import dev.tanay.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return mapToGenericDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto response = fakeStoreProductServiceClient.getProductById(id);
        if(response == null){
            throw new NotFoundException("Product with id: "+id+" doesn't exist.");
        }
        return mapToGenericDto(response);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProducts = fakeStoreProductServiceClient.getAllProducts();
        return convertToGenericDto(fakeStoreProducts);
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) {
        return mapToGenericDto(fakeStoreProductServiceClient.updateProductById(product, id));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return mapToGenericDto(fakeStoreProductServiceClient.deleteProductById(id));
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
