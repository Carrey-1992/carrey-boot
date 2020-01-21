package com.example.carrey.设计模式.构建者模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description Builder
 * @create 2020-01-21 10:49
 */
public interface Builder {

  /**
   * 构建部件A
   */
  void buildPartA();

  /**
   * 构建部件B
   */
  void buildPartB();

  /**
   * 获取商品
   * @return
   */
  Product getResult();
}
