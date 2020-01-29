package com.stackroute.config;

import com.stackroute.kafka.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

public class KafkaBuyerConsumerConfig {
    @Bean
    public ConsumerFactory<String, BuyerRecomDto> consumeBuyerFactory(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"buyer-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(BuyerRecomDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BuyerRecomDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, BuyerRecomDto> factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumeBuyerFactory());
        return factory;
    }
    @Bean
    public ConsumerFactory<String, SellerRecomDto> consumeSellerFactory(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"seller-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(SellerRecomDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SellerRecomDto> kafkaListenerContainerFactory1(){
        ConcurrentKafkaListenerContainerFactory<String, SellerRecomDto> factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumeSellerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ProductRecomDto> consumeProductFactory(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"product-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(ProductRecomDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductRecomDto> kafkaListenerContainerFactory2(){
        ConcurrentKafkaListenerContainerFactory<String, ProductRecomDto>
                factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumeProductFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, NewSellerDto> consumeNewSellerFactory(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"new-seller-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(NewSellerDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NewSellerDto> kafkaListenerContainerFactory3(){
        ConcurrentKafkaListenerContainerFactory<String, NewSellerDto>
                factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumeNewSellerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, BookRecomDto> consumeBookFactory(){
        Map<String,Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"book-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(BookRecomDto.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookRecomDto> kafkaListenerContainerFactory4(){
        ConcurrentKafkaListenerContainerFactory<String, BookRecomDto>
                factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumeBookFactory());
        return factory;
    }

}
