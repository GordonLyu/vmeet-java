//package com.socketserver.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author 像风如你
// * @since 2023/6/19
// */
//
//@Configuration
//@EnableKafka
//public class KafkaConfig {
//
//    // Kafka 服务器地址
//    private static final String KAFKA_SERVERS = "localhost:9092";
//
//    // 消费者组ID
//    private static final String GROUP_ID = "websocket_group";
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVERS);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        // 其他 Kafka 配置属性可以在这里添加
//
//        return new DefaultKafkaConsumerFactory<>(properties);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//
//        return factory;
//    }
//}
