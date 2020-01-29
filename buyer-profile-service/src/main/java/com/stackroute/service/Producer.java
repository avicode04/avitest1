package com.stackroute.service;

import com.stackroute.kafka.BuyerDto;
import com.stackroute.kafka.BuyerRecomDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    private static final Logger logger= LoggerFactory.getLogger(Producer.class);

    @Value("${TOPIC_BUYER_DTO}")
    private  String buyerTopic;

    @Value("${TOPIC_BUYER_RECOM_DTO}")
    private  String buyerRecomTopic;

    @Autowired
    private KafkaTemplate<String, BuyerDto> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, BuyerRecomDto> kafkaTemplate1;

    public void sendMessageBuyerDto( BuyerDto buyerDto){
        logger.info(String.format("#### -> Producing message -> %s",buyerDto));
        kafkaTemplate.send(buyerTopic,buyerDto);
    }

    public void sendMessageBuyerRecomDto(BuyerRecomDto buyerRecomDto){
        logger.info(String.format("#### -> Producing message -> %s",buyerRecomDto));
        kafkaTemplate1.send(buyerRecomTopic,buyerRecomDto);
    }
}
