package com.carrey.demo.config.exception;

/**
 * @author Conway
 * @className ExceptionConst
 * @description
 * @date 2020/12/3 上午11:56
 */
public class ExceptionConst {
    /**
     * 未知异常
     */
    public static String UNKNOWN_ERROR_CODE = "0";

    /**
     * 内部异常
     */
    public static String ERROR_CODE = "1";

    /**
     * 服务内部抛出异常内部拒绝
     */
    public static String REJECT_ERROR_CODE = "2";

    /**
     * 服务内部参数异常
     */
    public static String PARAM_ERROR_CODE = "3";
}
