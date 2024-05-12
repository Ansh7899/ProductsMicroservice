package com.learningkafka.ProductsMicroservice.service.implementation;

import com.learningkafka.ProductsMicroservice.models.Product;
import com.learningkafka.ProductsMicroservice.service.ProductCreatedEvent;
import com.learningkafka.ProductsMicroservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// Marking this class as a special spring component
@Service
public class ProductServiceImplementation implements ProductService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImplementation(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate=kafkaTemplate    ;
    }

    @Override
    public String createProduct(Product product) {
        String productId = UUID.randomUUID().toString();

//        TODO: Save event to DB before publishing it to DB

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                        product.getTitle(),
                        product.getPrice(),
                        product.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> completableFuture = kafkaTemplate.send(
                "product-created-events-topic",
                productId,
                productCreatedEvent);

        completableFuture.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Failed to write message to kafka due to: {}", exception.getMessage());
            } else {
                log.info("Message persisted to kafka successfully for: {}", result.getRecordMetadata());
            }
        });

//        will wait till completion of futurable object and block the thread
//        will convert the operation to sync mode
//        completableFuture.join();

        return "";
    }
}
