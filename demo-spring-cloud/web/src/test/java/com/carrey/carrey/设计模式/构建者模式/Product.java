package com.carrey.carrey.设计模式.构建者模式;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author Carrey
 * @version 0.0.1
 * @description Product类-产品类，由多个部件组成
 * @create 2020-01-21 10:36
 */
public class Product {

  List<String> parts = Lists.newArrayList();

  /**
   * 添加产品部件
   * @param part
   */
  public void add(String part) {
    parts.add(part);
  }

  public void show() {
    System.out.println("\n产品 创建----");
    for (String part :parts) {
      System.out.println(part);
    }
  }
}
