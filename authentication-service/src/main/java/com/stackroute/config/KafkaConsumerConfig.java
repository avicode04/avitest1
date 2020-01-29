package com.stackroute.config;


import com.stackroute.kafka.BuyerDto;
import com.stackroute.kafka.SellerDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, SellerDto> sellerDtoConsumerFactory()
    {
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"seller-id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<String,SellerDto>(config,new StringDeserializer(),new JsonDeserializer<>(SellerDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,SellerDto> kafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String,SellerDto> factory=new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(sellerDtoConsumerFactory());
        return factory;
    }
    @Bean
    public ConsumerFactory<String, BuyerDto> buyerDtoConsumerFactory()
    {
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"buyer-id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<String,BuyerDto>(config,new StringDeserializer(),new JsonDeserializer<>(BuyerDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,BuyerDto> kafkaListenerContainerFactory1()
    {
        ConcurrentKafkaListenerContainerFactory<String,BuyerDto> factory=new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(buyerDtoConsumerFactory());
        return factory;
    }


}


