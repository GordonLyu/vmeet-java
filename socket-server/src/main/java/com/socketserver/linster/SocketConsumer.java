//package com.socketserver.linster;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//
//import java.util.Arrays;
//import java.util.*;
//
//import static com.socketserver.config.WebSocketServer.sendMessage;
//
///**
// * @author 像风如你
// * @since 2023/5/27
// */
//
//public class SocketConsumer extends Thread {
//
//    @Override
//    public void run() {
//        Properties prop = new Properties();
//        System.out.println("启动kafka消费者....");
//        prop.put("bootstrap.servers", "cdh3:9092");
//        prop.put("group.id", "socket");
//        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
////如果是之前存在的group.id
//        Consumer consumer = new KafkaConsumer(prop);
//        consumer.subscribe(Arrays.asList("zeek_test"));
//        while (true) {
//            ConsumerRecords<String, String> c = consumer.poll(100);
//            for (ConsumerRecord<String, String> c1 : c) {
//                System.out.println(c1.value());
//                sendMessage("socket", c1.value());
//            }
//        }
//    }
//}