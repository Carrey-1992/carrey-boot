package com.carrey.demo.config.exception;

/**
 * @author Conway
 * @className CarreyFaileException
 * @description
 * @date 2020/12/2 下午5:00
 */
public class CarreyFailedException extends Exception{

    private String code = ExceptionConst.REJECT_ERROR_CODE;

    private String errorMessage;

    public CarreyFailedException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CarreyFailedException(String errorMessage,Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public CarreyFailedException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }
}
