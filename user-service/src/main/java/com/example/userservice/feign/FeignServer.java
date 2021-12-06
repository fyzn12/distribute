package com.example.userservice.feign;

import com.example.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/6 11:27
 * @description
 */
@FeignClient(name="user-service")
public interface FeignServer {
    /**
     *   <p>
     *       feign接口调用测试
     *   </P>
     * @param: userId
     * @author:  zhang.rongjun
     * @DateTime: 2021/12/3 16:17
     * */
    @GetMapping("/server/getUserById/{userId}")
    User getUserById(@PathVariable Long userId);
}
