package com.simple.chapter18.entity;

/**
 * 异常信息模板
 *
 * @author yxxcoder
 * @since 2019-03-19 22:23 PM
 */
public class ErrorResponseEntity {

    private int code;
    private String message;

    public ErrorResponseEntity() {
    }

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
