package com.carrey.carrey.domain.bean;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carrey
 * @className CTest
 * @description CTest
 * @date 2021/9/16 4:19 下午
 */
@Service
public class CTest implements ITest<C>{
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public List<C> getList() {
        System.out.println("我是C");
        return new ArrayList<>();
    }
}
