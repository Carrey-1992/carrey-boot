package com.carrey.carrey.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 枚举参数转换通用接口(注：不可和IntCodeToEnum同时被实现).
 *
 * <p>主要用于controller层form传参对于strCode参数转化成相应枚举、
 * 或对枚举进行反序列化时根据相应的strCode参数转化成相应枚举<p/>
 */
public interface StrCodeToEnum {

  /**
   * 返回string类型的code码.
   *
   * @return
   */
  @JsonValue
  String getStrCode();
}
