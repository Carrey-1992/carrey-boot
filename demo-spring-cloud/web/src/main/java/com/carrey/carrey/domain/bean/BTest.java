package com.carrey.carrey.domain.bean;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carrey
 * @className BTest
 * @description BTest
 * @date 2021/9/16 4:19 下午
 */
@Service
public class BTest implements ITest<B>{
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public List<B> getList() {
        System.out.println("我是B");
        return new ArrayList<>();
    }
}
