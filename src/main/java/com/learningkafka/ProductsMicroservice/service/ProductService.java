package com.learningkafka.ProductsMicroservice.service;

import com.learningkafka.ProductsMicroservice.models.Product;

public interface ProductService {
    String createProduct(Product product);
}
