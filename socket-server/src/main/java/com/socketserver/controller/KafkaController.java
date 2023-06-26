package com.socketserver.controller;

import com.socketserver.linster.sender.EsKafkaMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 像风如你
 * @since 2023/6/19
 */

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private EsKafkaMessageSender sender;

    @PostMapping("/KafkaMessageSend")
    public void testKafkaMessageSend(@RequestBody String message) {
        sender.sendToDefaultChannel(message);
    }

    @PostMapping("/KafkaMessageSend2")
    public void testKafkaMessageSend2(@RequestBody String message) {
        sender.sendToAlarmChannel(message);
    }

}
