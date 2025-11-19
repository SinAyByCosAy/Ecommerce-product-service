package dev.tanay.productservice.thirdpartyclients.adapters;

import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.thirdpartyclients.clients.FakeStoreProductServiceClient;
import dev.tanay.productservice.thirdpartyclients.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreServiceAdapter implements ThirdPartyProductServiceAdapter {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreServiceAdapter(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        return convertToGenericDto(fakeStoreProductServiceClient.getAllProducts());
    }

    @Override
    public GenericProductDto getProductById(Long id){
        return mapToGenericDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return mapToGenericDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto deleteProduct(Long id){
        return mapToGenericDto(fakeStoreProductServiceClient.deleteProduct(id));
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto product, Long id){
        return mapToGenericDto(fakeStoreProductServiceClient.updateProduct(product, id));
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
