package com.carrey.carrey.async;

import org.springframework.context.ApplicationEvent;

/**
 * @author Carrey
 * @version 0.0.1
 * @description DemoEvent
 * @create 2019-10-28 20:16
 */
public class DemoEvent extends ApplicationEvent {

  //事件监听消息
  private String msg;

  public DemoEvent(Object source, String msg) {
    super(source);
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

}
