package com.stackroute.config;

import com.stackroute.domain.OrderedProducts;
import com.stackroute.kafka.OrderedProductsDto;
import com.stackroute.kafka.ReturnedProductsDto;
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

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderedProductsDto> orderedProductsDto(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"ordered-products-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(OrderedProductsDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderedProductsDto> k(){
        ConcurrentKafkaListenerContainerFactory<String,OrderedProductsDto>
                factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderedProductsDto());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ReturnedProductsDto> returnedProductsDto(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"returned-products-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(ReturnedProductsDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,ReturnedProductsDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,ReturnedProductsDto>
                factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(returnedProductsDto());
        return factory;
    }
}
