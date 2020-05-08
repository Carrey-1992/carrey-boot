package com.carrey.demohutool;

import cn.hutool.core.convert.ConverterRegistry;
import com.carrey.demohutool.convert.Customer;
import com.carrey.demohutool.convert.CustomerConverter;
import com.carrey.demohutool.convert.Supplier;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Conway
 * @className ConvertTest
 * @description
 * @date 2020/5/8 10:51 上午
 */
public class ConvertTest {
    @Test
    public void customerConvertTest() {
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        converterRegistry.putCustom(Customer.class, CustomerConverter.class);
        Supplier supplier = new Supplier();
        supplier.setCode("01");
        supplier.setName("测试");
        Customer customer = converterRegistry.convert(Customer.class, supplier);
        Assert.assertEquals(supplier.getCode(),customer.getCode());
        Assert.assertEquals(supplier.getName(),customer.getName());
    }
}
