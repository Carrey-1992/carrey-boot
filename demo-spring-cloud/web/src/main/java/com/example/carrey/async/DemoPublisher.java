package com.example.carrey.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Carrey
 * @version 0.0.1
 * @description DemoPubLisher
 * @create 2019-10-28 20:17
 */
@Component
public class DemoPublisher {

  @Autowired
  private ApplicationContext applicationContext;

  //事件发布方法
  public void pushListener(String msg) {
    applicationContext.publishEvent(new DemoEvent(this, msg));
  }
}
