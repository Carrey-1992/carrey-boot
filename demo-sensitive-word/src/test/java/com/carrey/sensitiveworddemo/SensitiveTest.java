package com.carrey.sensitiveworddemo;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Conway
 * @className SensitiveTest
 * @description
 * @date 2020/7/23 6:10 下午
 */
public class SensitiveTest {

    @Test
    public void test() {
        final String text = "五星红旗迎风飘扬，画像屹立在天安门前。";

        Assert.assertTrue(SensitiveWordBs.newInstance().contains(text));
    }

    @Test
    public void test2() {
        final String text = "我在天安门广场吃着炸鸡";

        String word = CarreySensitiveWordBs.newInstance().findFirst(text);
        List<String> all = CarreySensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("五星红旗", word);
    }

    @Test
    public void test3() {

        int n = 122;
        int m = 65;
        StringBuffer sbu = new StringBuffer();
        Random random=new Random();
        Stream.iterate(1, i -> ++i)
                .limit(100000000)
                .forEach(i -> {
                    sbu.append((char) Integer.parseInt(random.nextInt(n - m + 1) + m+""));
                });

        final String text = sbu.toString();
        long start = System.currentTimeMillis();
        List<String> all = CarreySensitiveWordBs.newInstance().findAll(text);
        System.out.println("花费时间："+ (System.currentTimeMillis() - start) +"毫秒");
    }
}
