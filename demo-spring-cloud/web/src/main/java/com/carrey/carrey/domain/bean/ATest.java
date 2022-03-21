package com.carrey.carrey.domain.bean;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carrey
 * @className ATest
 * @description ATest
 * @date 2021/9/16 4:18 下午
 */
@Service
public class ATest implements ITest<A>{
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public List<A> getList() {
        System.out.println("我是A");
        return new ArrayList<>();
    }
}
