package com.example.carrey.设计模式.状态模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description ForenoonState
 * @create 2020-01-22 14:58
 */
public class ForenoonState implements State {
  @Override
  public void writeProgram(WorkContext work) {
    if (work.getHour() < 12) {
      System.out.println(String.format("当前时间：%s点 上午工作 精神百倍",work.getHour()));
      return;
    }
    work.setState(new AfternoonState());
    work.writeProgram();
  }
}
