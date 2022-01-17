package com.example.kubernetes_demo.exception;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/7 16:22
 * @description
 */
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public GlobalException(String message) {
        this.message = message;
    }

    public GlobalException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public GlobalException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
