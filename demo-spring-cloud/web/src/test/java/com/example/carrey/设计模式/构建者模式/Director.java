package com.example.carrey.设计模式.构建者模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description Director 指挥者
 * @create 2020-01-21 13:45
 */
public class Director {
  /**
   * 构建
   */
  public void construt(Builder builder) {
    builder.buildPartA();
    builder.buildPartB();
  }
}
