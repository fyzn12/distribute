package com.example.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/2 17:37
 * @description
 */
@RestController
public class TestController {

    @GetMapping("b")
    public String test(){
        return "order";
    }
}
