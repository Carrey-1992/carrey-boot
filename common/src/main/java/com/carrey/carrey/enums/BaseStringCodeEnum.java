package com.carrey.carrey.enums;

/**
 * @author Carrey
 * @className BaseStringCodeEnum
 * @description BaseStringCodeEnum
 * @date 2021/9/16 4:44 下午
 */
public interface BaseStringCodeEnum {
    /**
     * 获取String类型的编码
     * <p>当枚举实现该方法后可以通过{@linkEnumUtils#getEnumsByStrCode}
     * 方法获取相应的枚举 <p/>
     * @return
     */
    String getStrCode();

    /**
     * 描述
     * @return
     */
    String getDesc();
}
