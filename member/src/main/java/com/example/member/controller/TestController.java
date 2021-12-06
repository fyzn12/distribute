package com.example.member.controller;


import com.example.common.entity.User;
import com.example.member.feign.FeignServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/2 16:00
 * @description
 */
@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private FeignServer feignServer;
    @GetMapping("a")
    public String test(){
        return "test";
    }


    @GetMapping("getUser/{userId}")
    public User getUser(@PathVariable Long userId){
        return feignServer.getUserById(userId);
    }
}
