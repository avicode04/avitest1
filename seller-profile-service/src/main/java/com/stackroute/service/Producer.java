package com.stackroute.service;

import com.stackroute.kafka.SellerDto;
import com.stackroute.kafka.SellerRecomDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class Producer {

    private static final Logger logger= LoggerFactory.getLogger(Producer.class);

    private static final String TOPIC="sellerinfo";

    private static final String TOPIC_SELLER_RECOM_DTO="sellerrecom";

    @Autowired
    private KafkaTemplate<String, SellerDto> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, SellerRecomDto> kafkaTemplate1;

    public void sendMessage( SellerDto sellerDto){
        logger.info(String.format("#### -> Producing message -> %s",sellerDto));
        kafkaTemplate.send(TOPIC,sellerDto);
    }

    public void sendMessageSellerRecomDto(SellerRecomDto sellerRecomDto){
        logger.info(String.format("#### -> Producing message -> %s",sellerRecomDto));
        kafkaTemplate1.send(TOPIC_SELLER_RECOM_DTO,sellerRecomDto);
    }
}
