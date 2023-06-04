package com.socketserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LvXinming
 * @since 2023/6/1
 */

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    String test(){
        return "测试";
    }
}
