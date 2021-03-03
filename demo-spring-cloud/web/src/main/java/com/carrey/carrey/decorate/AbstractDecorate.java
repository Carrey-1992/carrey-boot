package com.carrey.carrey.decorate;

import java.util.List;
import java.util.Objects;

/**
 * @author Conway
 * @className AbstractIdecorate
 * @description
 * @date 2020/12/30 下午5:53
 */
public abstract class AbstractDecorate implements Idecorate {

    protected Idecorate idecorate;

    @Override
    public List<String> process(List<String> list) {
        List<String> process = doProcess(list);
        if (Objects.nonNull(idecorate)) {
            return idecorate.process(process);
        }
        return process;
    }

    protected abstract List<String> doProcess(List<String> list);
}
