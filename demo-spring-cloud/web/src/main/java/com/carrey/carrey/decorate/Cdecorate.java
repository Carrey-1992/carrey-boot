package com.carrey.carrey.decorate;

import java.util.List;

/**
 * @author Conway
 * @className Cdecorate
 * @description
 * @date 2020/12/30 下午5:44
 */
public class Cdecorate extends AbstractDecorate {

    public Cdecorate(Idecorate idecorate) {
        this.idecorate = idecorate;
    }

    @Override
    public List<String> doProcess(List<String> list) {
        System.out.println("Cdecorate 在处理");
        //TODO
        return list;
    }

}
