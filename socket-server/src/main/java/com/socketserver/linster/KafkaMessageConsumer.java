//package com.socketserver.linster;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.socketserver.config.WebSocketServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
///**
// * kafka消费者类
// * @author 像风如你
// * @since 2023/6/19
// */
//
//@Component
//public class KafkaMessageConsumer {
//
//    private WebSocketServer webSocketServer;
//
//    @Autowired
//    public KafkaMessageConsumer(WebSocketServer webSocketServer) {
//        this.webSocketServer = webSocketServer;
//    }
//
//    @KafkaListener(topics = "websocket_topic")
//    public void receiveMessage(String message) throws Exception {
//        // 接收到 Kafka 中的消息
//        System.out.println(message);
//        // 将消息发送给相应的 WebSocket 客户端
//        webSocketServer.sendMessageAll(JSONObject.parseObject(message));
//    }
//}