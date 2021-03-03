package com.carrey.carrey.设计模式.构建者模式;

import org.junit.Test;

/**
 * @author Carrey
 * @version 0.0.1
 * @description BuilderTest
 * @create 2020-01-21 13:49
 */
public class BuilderTest {

  @Test
  public void test() {
    Director director = new Director();
    Builder builder1 = new ConcreteBuilder1();
    Builder builder2 = new ConcreteBuilder2();

    director.construt(builder1);
    Product p1 = builder1.getResult();
    p1.show();

    director.construt(builder2);
    Product p2 = builder2.getResult();
    p2.show();
  }
}
