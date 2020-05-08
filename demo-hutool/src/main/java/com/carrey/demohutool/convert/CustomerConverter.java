package com.carrey.demohutool.convert;

import cn.hutool.core.convert.Converter;
import cn.hutool.core.util.ReflectUtil;

import java.util.Optional;

/**
 * @author Conway
 * @className CustomerConverter
 * @description 自定义客户转换对象
 * @date 2020/5/8 10:18 上午
 */
public class CustomerConverter implements Converter<Customer> {
    @Override
    public Customer convert(Object value, Customer defaultValue) throws IllegalArgumentException {
        Customer customer = new Customer();
        Class<?> aClass = value.getClass();
        Optional.ofNullable(ReflectUtil.getField(aClass,"name"))
                .map(field -> ReflectUtil.getFieldValue(value, field.getName()))
                .map(String::valueOf)
                .ifPresent(customer::setName);
        Optional.ofNullable(ReflectUtil.getField(aClass,"code"))
                .map(field -> ReflectUtil.getFieldValue(value, field.getName()))
                .map(String::valueOf)
                .ifPresent(customer::setCode);
        return customer;
    }
}
