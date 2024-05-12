package com.learningkafka.ProductsMicroservice.service.implementation;

import com.learningkafka.ProductsMicroservice.models.Product;
import com.learningkafka.ProductsMicroservice.service.ProductCreatedEvent;
import com.learningkafka.ProductsMicroservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Marking this class as a special spring component
@Service
public class ProductServiceImplementation implements ProductService {

    @Override
    public String createProduct(Product product) {
        String productId = UUID.randomUUID().toString();

//        TODO: Save event to DB before publishing it to DB

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        return "";
    }
}
