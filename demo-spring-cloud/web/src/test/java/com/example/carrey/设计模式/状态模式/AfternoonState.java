package com.example.carrey.设计模式.状态模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description AfternoonState
 * @create 2020-01-22 15:01
 */
public class AfternoonState implements State {
  @Override
  public void writeProgram(WorkContext work) {
    if (work.getHour() < 17) {
      System.out.println(String.format("当前时间：%s点 下午状态还不错 继续努力",work.getHour()));
    }
  }
}
