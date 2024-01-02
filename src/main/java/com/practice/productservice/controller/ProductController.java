package com.practice.productservice.controller;

import com.practice.productservice.models.Product;
import com.practice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return null;
    }

    @PutMapping("/{id}")
    public Product updateCompleteProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id")Long id) {

    }
}
