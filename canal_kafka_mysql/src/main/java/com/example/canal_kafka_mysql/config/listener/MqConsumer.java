package com.example.canal_kafka_mysql.config.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/17 13:00
 * @description
 */
@Component
public class MqConsumer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "my_test_canal")
    public void kafkaConsumerListener(List<ConsumerRecord<String, String>> list, Acknowledgment ack){
        for (ConsumerRecord<String, String> consumer : list) {
            System.out.println("topic名称:" + consumer.topic() + ",key:" + consumer.key() + "," + "分区位置:" + consumer.partition() + ", 下标" + consumer.offset() + "," + consumer.value());
            String json = (String) consumer.value();
//        JSONObject jsonObject = JSONObject.parseObject(json);
            System.out.println(json);
        }
    }

}
