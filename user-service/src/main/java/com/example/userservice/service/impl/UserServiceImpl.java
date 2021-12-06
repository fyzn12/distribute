package com.example.userservice.service.impl;

import com.example.common.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/6 9:51
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Long userId) {
        return User.builder().userId(userId).userName("张三").build();
    }
}
