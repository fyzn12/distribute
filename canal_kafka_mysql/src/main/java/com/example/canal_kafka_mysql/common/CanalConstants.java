package com.example.canal_kafka_mysql.common;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/17 15:38
 * @description
 */
public interface CanalConstants {
    public static final String DEFAULT_HOST = "127.0.0.1";

    public static final int DEFAULT_PORT = 1111;

    public static final String DEFAULT_DESTINATION = "example";

    public static final String DEFAULT_USERNAME = "canal";

    public static final String DEFAULT_PASSWORD = "canal";

    public static final int DEFAULT_BATCHSIZE = 1000;

    public static final String DEFAULT_FILTER =".*\\..*";

    public static final boolean DEFAULT_CUSTOM = true;

    public static final String UNITY_TABLE = "common_table";

    public static final String UNITY_SCHEMA = "common_table";

    /**
     * 多过滤器 逗号分隔
     */
    public static final String DEFAULT_FILTER_SPLIT = ",";

    /**
     * 数据库与表名直接 点号分隔
     */
    public static final String DEFAULT_TABLE_SPLIT = "\\.";
}
