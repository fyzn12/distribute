//package com.example.canal_kafka_mysql.config.client;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import com.alibaba.otter.canal.protocol.CanalEntry.*;
//import com.alibaba.otter.canal.protocol.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author zhang.rongjun
// * @version 1.0
// * @date 2022/1/17 10:59
// * @description
// */
//@Component
//public class CanalClient {
//
//    @Autowired
//    @Qualifier("connector")
//    private CanalConnector connector;
//
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    public void listener(){
//        int batchSize = 100;
//        try {
//            connector.connect();
//            // 表示监听myblog.tbl_order库下的tbl_order表   myblog.*表示监听该库下所有表
//            connector.subscribe("myblog.*");
//            connector.rollback();
//            while (true) {
//                // 获取指定数量的数据
//                Message message = connector.getWithoutAck(batchSize);
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId == -1 || size == 0) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    printEntry(message.getEntries(),batchId);
//                }
//                // 提交确认
//                connector.ack(batchId);
//            }
//        } finally {
//            connector.disconnect();
//        }
//    }
//
//
//    private  void printEntry(List<Entry> entrys,long batchId) {
//        for (Entry entry : entrys) {
//            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
//                continue;
//            }
//            RowChange rowChage = null;
//            try {
//                rowChage = RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                // 处理失败, 回滚数据
//                connector.rollback(batchId);
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
//                        e);
//            }
//            EventType eventType = rowChage.getEventType();
//            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                    eventType));
//
//            for (RowData rowData : rowChage.getRowDatasList()) {
//                if (eventType == EventType.DELETE) {
//                    redisDelete(rowData.getBeforeColumnsList());
//                } else if (eventType == EventType.INSERT) {
//                    redisInsert(rowData.getAfterColumnsList());
//                } else {
//                    System.out.println("-------> before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------> after");
//                    redisUpdate(rowData.getAfterColumnsList());
//                }
//            }
//        }
//    }
//
//    private  void printColumn(List<Column> columns) {
//        for (Column column : columns) {
//            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
//
//    private  void redisInsert(List<Column> columns) {
//        JSONObject json = new JSONObject();
//        for (Column column : columns) {
//            json.put(column.getName(), column.getValue());
//        }
//        if (columns.size() > 0) {
//            redisTemplate.opsForSet().add(columns.get(0).getValue(),json.toJSONString());
//        }
//    }
//
//    private  void redisUpdate(List<Column> columns) {
//        JSONObject json = new JSONObject();
//        for (Column column : columns) {
//            json.put(column.getName(), column.getValue());
//        }
//        if (columns.size() > 0) {
//            redisTemplate.opsForSet().add(""+columns.get(0).getValue(), json.toJSONString());
//        }
//    }
//
//    private  void redisDelete(List<Column> columns) {
//        JSONObject json = new JSONObject();
//        for (Column column : columns) {
//            json.put(column.getName(), column.getValue());
//        }
//        if (columns.size() > 0) {
//            redisTemplate.delete(columns.get(0).getValue());
//        }
//    }
//
//}
