package com.example.carrey.spring;

import com.example.carrey.async.DemoEvent;
import com.example.carrey.domain.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Conway
 * @className ApplicationListenerTest
 * @description
 * @date 2020/11/10 10:05 上午
 */
public class ApplicationListenerTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        ctx.publishEvent(new DemoEvent(this,"我手动发布了一个事件"));
        ctx.close();
    }

}
