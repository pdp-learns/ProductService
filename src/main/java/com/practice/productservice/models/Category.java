package com.practice.productservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }
}
