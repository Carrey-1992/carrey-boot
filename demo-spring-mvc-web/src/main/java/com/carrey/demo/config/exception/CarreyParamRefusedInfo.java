package com.carrey.demo.config.exception;

/**
 * @author Conway
 * @className CarreyParamRefusedInfo
 * @description
 * @date 2020/12/3 下午12:19
 */
public class CarreyParamRefusedInfo {

    /**
     * 验证不通过的位置,可以是字段,也可以是标记
     */
    private String fieldName;

    /**
     * 验证不通过的原因
     */
    private String errorMessage;

    /**
     * 数组返回列表异常坐标
     */
    private Integer index;

    /**
     * 验证不通过的原始值
     */
    private Object errorValue;

}
