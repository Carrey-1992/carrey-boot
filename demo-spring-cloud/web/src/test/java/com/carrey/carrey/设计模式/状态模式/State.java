package com.carrey.carrey.设计模式.状态模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description State 状态接口
 * @create 2020-01-22 14:52
 */
public interface State {
  /**
   * 编写程序
   * @param work
   */
  void writeProgram(WorkContext work);
}
