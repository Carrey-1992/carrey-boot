package com.carrey.carrey.decorate;

import java.util.List;

/**
 * @author Conway
 * @className FilterProcessImpl
 * @description
 * @date 2020/12/30 下午5:58
 */
public class DecorateContext implements FilterProccess {

    protected Idecorate idecorate;

    public DecorateContext(Idecorate idecorate) {
        this.idecorate = idecorate;
    }

    @Override
    public List<String> filter(List<String> list) {
        return idecorate.process(list);
    }
}
