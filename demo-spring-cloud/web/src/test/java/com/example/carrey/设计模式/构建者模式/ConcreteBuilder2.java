package com.example.carrey.设计模式.构建者模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description ConcreteBuilder2
 * @create 2020-01-21 13:43
 */
public class ConcreteBuilder2 implements Builder{

  private Product product = new Product();

  @Override
  public void buildPartA() {
    System.out.println("部件X");
  }

  @Override
  public void buildPartB() {
    System.out.println("部件Y");
  }

  @Override
  public Product getResult() {
    return product;
  }
}
