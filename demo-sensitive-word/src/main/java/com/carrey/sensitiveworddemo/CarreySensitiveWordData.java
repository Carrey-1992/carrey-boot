package com.carrey.sensitiveworddemo;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.constant.AppConst;
import com.github.houbb.sensitive.word.support.data.SensitiveWordData;

import java.util.List;

/**
 * @author Conway
 * @className CarreySensitiveWordData
 * @description
 * @date 2020/7/23 6:37 下午
 */
public class CarreySensitiveWordData implements IWordData {

    /**
     * 默认的内置行
     *
     * @since 0.0.1
     */
    private static List<String> defaultLines;


    static {
        synchronized (SensitiveWordData.class) {
            long start = System.currentTimeMillis();
            defaultLines = Guavas.newArrayList(AppConst.DICT_SIZE+AppConst.DICT_EN_SIZE);
//            defaultLines = StreamUtil.readAllLines("/dict.txt");
//            defaultLines.addAll(StreamUtil.readAllLines("/dict_en.txt"));
            //defaultLines.add("天安门");
            //defaultLines.add("天安门广场");
            defaultLines.add("安门广场");
            long end = System.currentTimeMillis();
            System.out.println("Sensitive data loaded!, cost time: " + (end - start) + "ms");
        }
    }

    @Override
    public List<String> getWordData() {
        return defaultLines;
    }

}
