package com.example.carrey.设计模式.构建者模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description ConcreteBuilder1
 * @create 2020-01-21 13:42
 */
public class ConcreteBuilder1 implements Builder{

  private Product product = new Product();

  @Override
  public void buildPartA() {
    System.out.println("部件A");
  }

  @Override
  public void buildPartB() {
    System.out.println("部件B");
  }

  @Override
  public Product getResult() {
    return product;
  }
}
