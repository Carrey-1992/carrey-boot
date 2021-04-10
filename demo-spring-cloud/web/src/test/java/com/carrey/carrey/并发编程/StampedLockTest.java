package com.carrey.carrey.并发编程;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

  final StampedLock sl = new StampedLock();

  @Test
  public void test() {
    // 乐观读
    long stamp = sl.tryOptimisticRead();
    // 读入方法局部变量
    //......
    // 校验 stamp
    if (!sl.validate(stamp)) {
      // 升级为悲观读锁
      stamp = sl.readLock();
      try {
        // 读入方法局部变量
        //.....
      } finally {
        // 释放悲观读锁
        sl.unlockRead(stamp);
      }
    }
    // 使用方法局部变量执行业务操作
    //......

  }


  class Point {
    private int x, y;
    private final StampedLock lock = new StampedLock();
    //计算到原点的距离
    double distanceFromOrigin() {
      //乐观度
      long stamp = lock.tryOptimisticRead();
      //读入局部变量，在读的过程中可能被其他线程修改
      int curX = x;
      int curY = y;
      //判断执行读操作过程中是否被其他线程修改
      //lock.validate(stamp)返回false则证明被修改
      if (!lock.validate(stamp)) {
        //升级为悲观读锁，使x,y具有可见性，
        // 且在读取x,y的过程中不允许其他线程进行修改
        stamp = lock.readLock();
        try {
          curX = x;
          curY = y;
        }finally {
          //释放悲观读锁
          lock.unlockRead(stamp);
        }
      }
      return Math.sqrt( curX * curX + curY * curY);
    }

    void setValue(int x,int y) {
      long stamp = lock.writeLock();
      try {
        this.x = x;
        this.y = y;
      }finally {
        lock.unlockWrite(stamp);
      }
    }
  }

}
