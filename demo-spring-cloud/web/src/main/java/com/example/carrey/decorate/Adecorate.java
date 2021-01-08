package com.example.carrey.decorate;

import java.util.List;

/**
 * @author Conway
 * @className Adecorate
 * @description
 * @date 2020/12/30 下午5:43
 */
public class Adecorate extends AbstractDecorate {

    public Adecorate(Idecorate idecorate) {
        this.idecorate = idecorate;
    }

    @Override
    public List<String> doProcess(List<String> list) {
        System.out.println("Adecorate 在处理");
        //TODO
        return list;
    }
}
