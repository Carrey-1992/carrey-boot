package com.carrey.carrey.decorate;

import java.util.List;

/**
 * @author Conway
 * @className Bdecorate
 * @description
 * @date 2020/12/30 下午5:43
 */
public class Bdecorate extends AbstractDecorate {

    public Bdecorate(Idecorate idecorate) {
        this.idecorate = idecorate;
    }

    @Override
    public List<String> doProcess(List<String> list) {
        System.out.println("Bdecorate 在处理");
        //TODO
        return list;
    }
}
