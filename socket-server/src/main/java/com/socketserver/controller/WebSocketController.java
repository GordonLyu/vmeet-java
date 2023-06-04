package com.socketserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LvXinming
 * @since 2023/6/1
 */
<<<<<<<< HEAD:socket-server/src/main/java/com/socketserver/controller/WebSocketController.java
@RestController
@RequestMapping("/socket")
public class WebSocketController {

========

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {
>>>>>>>> 45fd327 (添加业务模块以及邮箱模块):socket-server/src/main/java/com/socketserver/controller/TestController.java
    @GetMapping("/test")
    String test(){
        return "测试";
    }
}
