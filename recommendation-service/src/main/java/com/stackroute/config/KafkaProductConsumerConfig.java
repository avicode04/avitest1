//package com.stackroute.config;
//
//import com.stackroute.kafka.ProductRecomDto;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//
//public class KafkaProductConsumerConfig {
//    @Bean
//    public ConsumerFactory<String, ProductRecomDto> consumeProductFactory(){
//        Map<String,Object> props= new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,"product-id");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(ProductRecomDto.class));
//    }
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, ProductRecomDto> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, ProductRecomDto>
//                factory= new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumeProductFactory());
//        return factory;
//    }
//}
