package com.example.userservice.controller;

import com.example.common.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/6 11:28
 * @description
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /**
     *   <p>
     *       feign接口调用测试
     *   </P>
     * @param: userId
     * @author:  zhang.rongjun
     * @DateTime: 2021/12/3 16:17
     * */
    @GetMapping("/server/getUserById/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
}
