package com.utkarsh.security.controller;

import com.utkarsh.security.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    List<Product> products = new ArrayList<>(List.of(
            new Product(1L, "iPhone", 999.0),
            new Product(2L, "Mac Pro", 1000.0)
    ));

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product saveProduct(@RequestBody Product product) {
        LOGGER.info("received request for product: {}", product);
        products.add(product);
        return product;
    }
}
