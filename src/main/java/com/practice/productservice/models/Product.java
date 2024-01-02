package com.practice.productservice.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private double price;
    private Category category;
    private String description;
    private String imageUrl;

    public Product(String title, double price, String category, String description, String imageUrl) {
        this.title = title;
        this.price = price;
        this.category = new Category(category);
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Product() {
    }
}
