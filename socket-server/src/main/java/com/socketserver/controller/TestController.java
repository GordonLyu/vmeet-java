package com.socketserver.controller;

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
@RequestMapping("")
@CrossOrigin
public class TestController {

    @GetMapping("/test/a")
    String test1(){
        return "测试a";
    }

    @GetMapping("/test/b")
    String test2(){
        return "测试b";
    }

    @GetMapping("/test1/a")
    String test3(){
        return "测试1a";
    }

    @GetMapping("/test1/b")
    String test4(){
        return "测试1b";
    }
}
