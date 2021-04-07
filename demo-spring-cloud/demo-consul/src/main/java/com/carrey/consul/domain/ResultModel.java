package com.carrey.consul.domain;

/**
 * @author Conway
 * @className ResultModel
 * @description
 * @date 2021/4/7 5:58 下午
 */
public class ResultModel {
    private String errorCode;
    private String message;
    private Object data;

    public ResultModel() {

    }

    public ResultModel(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResultModel(String errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultCode resultCodeEnum, Object data) {
        this.errorCode = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
        this.data = data;
    }

    public ResultModel(ResultCode resultCodeEnum) {
        this.errorCode = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
    }

    public String geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultModel ok() {
        return new ResultModel(ResultCode.CODE_00000);
    }

    public static ResultModel ok(Object data) {
        return new ResultModel(ResultCode.CODE_00000, data);
    }

    public static ResultModel error() {
        return new ResultModel(ResultCode.CODE_00001);
    }

    public static ResultModel error(String msg) {
        return new ResultModel(ResultCode.CODE_00001.getCode(), msg);
    }

    public static ResultModel error(String msg, Object data) {
        return new ResultModel(ResultCode.CODE_00001.getCode(), msg, data);
    }

    public static ResultModel unAuth() {
        return new ResultModel(ResultCode.CODE_40004);
    }
}
