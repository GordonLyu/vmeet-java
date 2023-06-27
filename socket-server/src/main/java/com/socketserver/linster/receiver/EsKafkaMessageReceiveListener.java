package com.socketserver.linster.receiver;

import com.socketserver.linster.EsChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.time.LocalDateTime;


/**
 * 消息订阅监听类
 * kafka从不同的通道实现消息的订阅。
 * @author 像风如你
 * @since 2023/6/19
 */

@Slf4j
@EnableBinding(value = EsChannel.class)
public class EsKafkaMessageReceiveListener {

    /**
     * 从缺省通道接收消息
     * @param message
     */
    @StreamListener(EsChannel.WS_DEFAULT_INPUT)
    public void receive(Message<String> message){
        log.info("{}订阅告警消息：通道 = ws_default_input(缺省通道)，data = {}", LocalDateTime.now(), message);
    }

    /**
     * 从告警通道接收消息
     * @param message
     */
//    @StreamListener(EsChannel.WS_ALARM_INPUT)
//    public void receiveAlarm(Message<String> message){
//        System.out.println("订阅告警消息：" + message);
//        log.info("{}订阅告警消息：通道 = ws_alarm_input，data = {}", LocalDateTime.now(), message);
//    }
}