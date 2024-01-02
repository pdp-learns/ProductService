package com.practice.productservice.service;

import com.practice.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();

    Product addNewProduct(Product product);
}
