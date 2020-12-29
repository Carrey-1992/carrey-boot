package com.carrey.demo.config.exception;

/**
 * @author Conway
 * @className CarreyError
 * @description
 * @date 2020/12/3 上午11:44
 */
public class CarreyErrorInfo {
    /**
     * 异常编码
     */
    private String errorCode;

    /**
     * 未知错误
     */
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CarreyErrorInfo(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CarreyErrorInfo(String errorCode) {
        this.errorCode = errorCode;
    }

    public CarreyErrorInfo() {
    }
}
