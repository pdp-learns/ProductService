package com.practice.productservice.service;

import com.practice.productservice.dto.FakeStoreProductDto;
import com.practice.productservice.models.Category;
import com.practice.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto responseObject = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        return convertDTOToModel(responseObject);
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
        });
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto rd : response.getBody()) {
            products.add(convertDTOToModel(rd));
        }
        return products;
    }

    @Override
    public Product addNewProduct(Product product) {
        ResponseEntity<FakeStoreProductDto> httpResponse = null;

        Map<String, Object> requestMap = getProductRequestMap(product);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestMap);
        httpResponse = restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.POST, entity, FakeStoreProductDto.class);

        FakeStoreProductDto respFakeStoreProductDto = httpResponse.getBody();

        return convertDTOToModel(respFakeStoreProductDto);
    }

    private Map<String, Object> getProductRequestMap(Product product) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("title", product.getTitle());
        requestMap.put("price", product.getPrice());
        requestMap.put("description", product.getDescription());
        requestMap.put("image", product.getImageUrl());
        requestMap.put("category", product.getCategory().getName());
        return requestMap;
    }


    private Product convertDTOToModel(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        Category category = new Category();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getTitle());
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
