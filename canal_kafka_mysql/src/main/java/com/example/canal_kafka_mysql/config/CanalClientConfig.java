//package com.example.canal_kafka_mysql.config;
//
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetSocketAddress;
//
///**
// * @author zhang.rongjun
// * @version 1.0
// * @date 2022/1/17 10:05
// * @description
// */
//@Configuration
//@Slf4j
//public class CanalClientConfig {
//
//    @Autowired
//    private CanalConfig canalConfig;
//
//    @Bean
//    public CanalConnector connector(){
//        //目前canal server上的一个instance只能有一个client消费, 当有多个client消费时,会有bug
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(canalConfig.getHost(),
//                canalConfig.getPort()), canalConfig.getDestination(), canalConfig.getUserName(), canalConfig.getPassword());
//        log.info("canal init : host : {} port : {}",canalConfig.getHost(),canalConfig.getPort());
//        connector.connect();
//        connector.subscribe(canalConfig.getFilter());
//        connector.rollback();
//        return connector;
//    }
//
//}
