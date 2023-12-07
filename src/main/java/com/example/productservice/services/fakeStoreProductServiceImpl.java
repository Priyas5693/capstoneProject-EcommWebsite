package com.example.productservice.services;

import com.example.productservice.ThirdPartyClient.FakeStoreClient.FakeStoreClientAdapter;
import com.example.productservice.dto.GenericDTO;
import com.example.productservice.dto.fakeStoreProductDtos;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductServiceImpl")
public class fakeStoreProductServiceImpl implements ProductService {
     private FakeStoreClientAdapter fakeStoreClientAdapter;
     fakeStoreProductServiceImpl(FakeStoreClientAdapter fakeStoreClientAdapter){
         this.fakeStoreClientAdapter=fakeStoreClientAdapter;
     }
    //Method to convert GenericStore DTO to FakeStore
    public GenericDTO convertGenericToFakeStore(fakeStoreProductDtos fakeStoreProductDtos){
       GenericDTO genericDTO= new GenericDTO();
       genericDTO.setId(fakeStoreProductDtos.getId());
       genericDTO.setCategory(fakeStoreProductDtos.getCategory());
       genericDTO.setDescription(fakeStoreProductDtos.getDescription());
       genericDTO.setTitle(fakeStoreProductDtos.getTitle());
       genericDTO.setPrice(fakeStoreProductDtos.getPrice());
       genericDTO.setImage(fakeStoreProductDtos.getImage());

       return genericDTO;
    }
    //exception handler are in controller
    @Override
    public GenericDTO getProductByID(Long id) throws ProductNotFoundException {
        return convertGenericToFakeStore(fakeStoreClientAdapter.getProductByID(id));
    }

    @Override
    public List<GenericDTO> getAllProduct() {
        List<GenericDTO> genericDTOList = new ArrayList<>();
        List<fakeStoreProductDtos> fakeStoreProductDtosList= fakeStoreClientAdapter.getAllProduct() ;
        for(fakeStoreProductDtos fakeStoreProductDtos:fakeStoreProductDtosList){
            genericDTOList.add(convertGenericToFakeStore(fakeStoreProductDtos));
        }
       return genericDTOList;
    }

    @Override
    public GenericDTO deleteProductByID(Long id) {
        return convertGenericToFakeStore(fakeStoreClientAdapter.deleteProductByID(id));
    }

    @Override
    public GenericDTO createProduct(GenericDTO genericdto) {
        return convertGenericToFakeStore(fakeStoreClientAdapter.createProduct(genericdto));

    }

    @Override
    public void updateProductByID() {
         //return fakeStoreClientAdapter.updateProductByID(id);

    }
}
