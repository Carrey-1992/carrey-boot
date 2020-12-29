package com.carrey.demo.config;

import com.carrey.demo.config.exception.CarreyErrorInfo;
import com.carrey.demo.config.exception.CarreyRefusedInfo;

/**
 * @author Conway
 * @className CarreyResult
 * @description
 * @date 2020/12/2 下午4:34
 */
public class CarreyResult {

    /**
     * 成功
     */
    public static final String CODE_SUCCESS = "200";

    /**
     * 业务异常
     */
    public static final String CODE_REFUSED = "207";

    /**
     * 系统异常
     */
    public static final String CODE_FAILED = "509";

    /**
     * 返回结果编码
     */
    private String resCode;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回对象
     */
    private Object resData;

    /**
     * 异常信息
     */
    private CarreyErrorInfo exception;


    public CarreyResult(String resCode, String message, CarreyErrorInfo exception) {
        this.resCode = resCode;
        this.message = message;
        this.exception = exception;
    }

    private CarreyResult(String resCode, String message, Object resData) {
        this.resCode = resCode;
        this.message = message;
        this.resData = resData;
    }

    /**
     * 获取成功结果
     * @param resData
     * @return
     */
    public static CarreyResult success(Object resData) {
        return new CarreyResult(CODE_SUCCESS,"request is ok",resData);
    }

    /**
     * 获取被拒绝结果
     * @param errorInfo
     * @return
     */
    public static CarreyResult refused(CarreyRefusedInfo errorInfo) {
        return new CarreyResult(CODE_REFUSED,"request is refused",errorInfo);
    }

    /**
     * 获取异常结果
     * @param errorInfo
     * @return
     */
    public static CarreyResult failed(CarreyErrorInfo errorInfo) {
        return new CarreyResult(CODE_FAILED,"request is failed",errorInfo);
    }

    public String getResCode() {
        return resCode;
    }

    public String getMessage() {
        return message;
    }

    public Object getResData() {
        return resData;
    }

    public CarreyErrorInfo getException() {
        return exception;
    }
}
