package dev.tanay.productservice.services;

import dev.tanay.productservice.thirdpartyclients.productservice.adapters.ThirdPartyProductServiceAdapter;
import dev.tanay.productservice.thirdpartyclients.productservice.dtos.FakeStoreProductDto;
import dev.tanay.productservice.dtos.GenericProductDto;
import dev.tanay.productservice.exceptions.NotFoundException;
import dev.tanay.productservice.thirdpartyclients.productservice.clients.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
public class FakeStoreProductService implements ProductService{
    private ThirdPartyProductServiceAdapter thirdPartyProductServiceAdapter;
    public FakeStoreProductService(ThirdPartyProductServiceAdapter thirdPartyProductServiceAdapter){
        this.thirdPartyProductServiceAdapter = thirdPartyProductServiceAdapter;
    }
    @Override
    public List<GenericProductDto> getAllProducts(){
        return thirdPartyProductServiceAdapter.getAllProducts();
    }

    @Override
    public GenericProductDto getProductById(Long id)throws NotFoundException {
        return thirdPartyProductServiceAdapter.getProductById(id);
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
