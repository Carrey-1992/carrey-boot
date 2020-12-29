package com.carrey.demo.config.exception;

/**
 * @author Conway
 * @className CarreyRefusedException
 * @description
 * @date 2020/12/2 下午4:56
 */
public class CarreyRefusedException extends RuntimeException{

    private String code = ExceptionConst.ERROR_CODE;

    public CarreyRefusedException(String errorMessage) {
        super(errorMessage);
    }

    public String getCode() {
        return code;
    }
}
