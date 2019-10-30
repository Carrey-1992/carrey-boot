package com.example.carrey.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Carrey
 * @version 0.0.1
 * @description ConfigAsync
 * @create 2019-10-28 20:58
 */
public class ConfigAsync {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoPublisher.class);
    //用注入的形式完成事件发布
    DemoPublisher pushListener = context.getBean(DemoPublisher.class);
    pushListener.pushListener("测试消息监听");
    pushListener.pushListener("测试消息监听1");

    context.close();

  }
}
