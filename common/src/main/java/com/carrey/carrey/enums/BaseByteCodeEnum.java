package com.carrey.carrey.enums;

/**
 * @author Carrey
 * @className BaseByteCodeEnum
 * @description BaseByteCodeEnum
 * @date 2021/9/16 4:42 下午
 */
public interface BaseByteCodeEnum {
    /**
     * 获取Byte类型的编码
     * <p>当枚举实现该方法后可以通过{@linkEnumUtils#getEnumsByByteCode}
     * 方法获取相应的枚举 <p/>
     * @return
     */
    Byte getByteCode();

    /**
     * 描述
     * @return
     */
    String getDesc();
}
