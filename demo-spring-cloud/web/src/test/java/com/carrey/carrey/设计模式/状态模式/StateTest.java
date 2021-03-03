package com.carrey.carrey.设计模式.状态模式;

import org.junit.Test;

/**
 * @author Carrey
 * @version 0.0.1
 * @description StateTest
 * @create 2020-01-22 14:51
 */
public class StateTest {

  @Test
  public void test() {
    WorkContext workContext = new WorkContext(9.0, new ForenoonState());
    workContext.writeProgram();
  }
}
