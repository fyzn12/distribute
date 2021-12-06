package com.example.userservice.service;

import com.example.common.entity.User;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/3 14:19
 * @description
 */
public interface UserService {

    /**
     *   <p>
     *       feign接口调用测试
     *   </P>
     * @param: userId
     * @author:  zhang.rongjun
     * @DateTime: 2021/12/3 16:17
     * */
    User getUserById(@PathVariable Long userId);
}
