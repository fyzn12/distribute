package com.example.kubernetes_demo.config;

import com.example.kubernetes_demo.exception.GlobalException;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/7 16:06
 * @description Kubernetes
 */
@Configuration
public class KubernetesClientConfiguration {
    private final static Logger log = LoggerFactory.getLogger(KubernetesClientConfiguration.class);

    @Value("${kubernetes.secret.token}")
    private String token;

    @Value("${kubernetes.master.requestUrl}")
    private String requestUrl;

    @Bean
    public ApiClient apiClient(){
        try {
            ApiClient client = new ClientBuilder()
                    .setBasePath(requestUrl)
                    .setVerifyingSsl(false)
                    .setAuthentication(new AccessTokenAuthentication(token.trim()))
                    .build();
            io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
            log.info("Kubernetes client initialization succeeded.");
            return client;
        }catch (Exception e){
            throw new GlobalException("Kubernetes client initialization is abnormal that the messages are {}",e.getMessage());
        }

    }
}
