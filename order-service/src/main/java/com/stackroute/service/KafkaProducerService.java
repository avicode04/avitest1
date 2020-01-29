package com.stackroute.service;

import com.stackroute.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

    private static final String TOPIC = "orderDetails";
    private static final Logger logger= LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderDetails(Order order){
        logger.info(String.format("#### -> Producing Order Details as follows -> %s", order));
        kafkaTemplate.send(TOPIC,order);
    }


}
