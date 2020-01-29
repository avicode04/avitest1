package com.stackroute.service;

import com.stackroute.domain.Order;
import com.stackroute.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "orderRefined";
    private static final String TOPIC_PRODUCT="product-Refined";
    private static final Logger logger= LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderDetails(Order order){
        logger.info(String.format("#### -> Producing Order Details as follows -> %s", order));
        kafkaTemplate.send(TOPIC,order);
    }

    @Autowired
    private KafkaTemplate<String, Product> kafkaTemplateProduct;

    public void sendProduct( Product product) {
        logger.info(String.format("#### -> Producing Product Details as follows -> %s", product));
        kafkaTemplateProduct.send(TOPIC_PRODUCT, product);
    }
}
