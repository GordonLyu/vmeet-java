package com.socketserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LvXinming
 * @since 2023/5/10
 * <p>测试接口</p>
 */
@RestController
@RequestMapping("/socket")
public class WebSocketController {

    @GetMapping("/test")
    String test(){
        return "测试";
    }
}
