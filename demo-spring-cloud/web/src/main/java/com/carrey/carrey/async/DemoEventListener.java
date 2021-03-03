package com.carrey.carrey.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Carrey
 * @version 0.0.1
 * @description DemoEventListener
 * @create 2019-10-28 20:18
 */
@Component
@Slf4j
public class DemoEventListener implements ApplicationListener<DemoEvent> {
  @Override
  public void onApplicationEvent(DemoEvent event) {
    String msg = event.getMsg();
    System.out.println("DemoEventListener,监听到了 DemoEvent 发布的消息:"+msg);
  }
}
