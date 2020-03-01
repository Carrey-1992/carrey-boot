package com.example.carrey.设计模式.代理模式.动态代理;

/**
 * 小明
 */
public class XiaoMing implements Spendthrift{
    @Override
    public void eat() {
        System.out.println("小明去和朋友吃饭！");
    }

    @Override
    public void drink() {
        System.out.println("小明去和朋友喝酒！");
    }

    @Override
    public void play() {
        System.out.println("小明想找点乐子玩玩！");
    }
}
