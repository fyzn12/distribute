package com.example.common.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/3 14:12
 * @description
 */
@Data
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String userName;
}
