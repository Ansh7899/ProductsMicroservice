package com.learningkafka.ProductsMicroservice.controller;

import com.learningkafka.ProductsMicroservice.models.Product;
import com.learningkafka.ProductsMicroservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        String response = productService.createProduct(product);
        return new ResponseEntity<>(response
                , HttpStatus.CREATED);
    }

}
