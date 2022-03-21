package com.carrey.carrey.enums;

/**
 * @author Carrey
 * @className BaseIntegerCodeEnum
 * @description BaseIntegerCodeEnum
 * @date 2021/9/16 4:43 下午
 */
public interface BaseIntegerCodeEnum {
    /**
     * 获取Integer类型的编码
     * <p>当枚举实现该方法后可以通过{@linkEnumUtils#getEnumsByIntCode}
     * 方法获取相应的枚举 <p/>
     * @return
     */
    Integer getIntCode();

    /**
     * 描述
     * @return
     */
    String getDesc();
}
