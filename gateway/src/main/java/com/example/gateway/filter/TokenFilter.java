package com.example.gateway.filter;

import com.auth0.jwt.interfaces.Claim;
import com.example.gateway.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/3 14:43
 * @description
 */
@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst("token");
        // 这里只是一个demo 只要携带 token 就能访问成功
        if (StringUtils.isBlank(token)) {
            log.error("认证失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        //优先级   数值越小越高
        return -1;
    }
}
