package com.carrey.demo.config.exception;

import java.util.List;

/**
 * @author Conway
 * @className CarreyRefusedInfo
 * @description
 * @date 2020/12/3 下午12:11
 */
public class CarreyRefusedInfo extends CarreyErrorInfo{

    private List<CarreyParamRefusedInfo> validationErrors;

    public CarreyRefusedInfo(String errorCode, List<CarreyParamRefusedInfo> validationErrors) {
        super(errorCode);
        this.validationErrors = validationErrors;
    }

    public CarreyRefusedInfo(String errorCode, String message) {
        super(errorCode, message);
    }

    public List<CarreyParamRefusedInfo> getValidationErrors() {
        return validationErrors;
    }
}
