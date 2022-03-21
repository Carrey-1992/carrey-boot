package com.carrey.carrey.domain.bean;

import java.util.List;

/**
 * @author Carrey
 * @className ITest
 * @description ITest
 * @date 2021/9/16 4:17 下午
 */
public interface ITest<T> {

    int getCount();

    List<T> getList();
}
