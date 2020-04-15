package com.example.carrey.设计模式.职责链模式;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author Carrey
 * @version 0.0.1
 * @description 请假处理责任链对象
 * @create 2020/4/15 10:38 上午
 */
public class TakeLeaveHandlerChain2 {

  private List<TakeLeaveHandler> takeLeaveHandlerList = Lists.newArrayList();

  public TakeLeaveHandlerChain2 setHandler(TakeLeaveHandler handler) {
    takeLeaveHandlerList.add(handler);
    return this;
  }

  public boolean doHandle(double day) {
    System.out.println("哦！你想请" + day + "天假啊...");
    for (TakeLeaveHandler takeLeaveHandler : takeLeaveHandlerList) {
      if (takeLeaveHandler.doTakeLeaveHandle(day)) {
        return true;
      }
    }
    return false;
  }

}
