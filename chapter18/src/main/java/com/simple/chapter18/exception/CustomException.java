package com.simple.chapter18.exception;

/**
 * 自定义异常
 *
 * @author yxxcoder
 * @since 2019-03-19 22:23 PM
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4564120621192825748L;

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
