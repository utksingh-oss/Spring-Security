package com.utkarsh.security.controller;

import com.utkarsh.security.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    List<Product> products = new ArrayList<>(List.of(
            new Product(1L, "iPhone", 999.0),
            new Product(2L, "Mac Pro", 1000.0)
    ));

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    public Product saveProduct(Product product) {
        products.add(product);
        return product;
    }
}
