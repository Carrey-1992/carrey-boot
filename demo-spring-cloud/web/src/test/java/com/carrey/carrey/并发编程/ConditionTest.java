package com.carrey.carrey.并发编程;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Carrey
 * @version 0.0.1
 * @description ConditionTest
 * @create 2019-11-11 15:09
 */
public class ConditionTest {

  @Test
  public void test() throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(2);

    BlockedQueue<Integer> blockedQueue = new BlockedQueue<>();

    new Thread(() -> {
      for (int i = 1; i <= 11; i++) {
        try {
          blockedQueue.enq(i);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      countDownLatch.countDown();
    }).start();

    new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        try {
          blockedQueue.deq();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      countDownLatch.countDown();
    }).start();
    countDownLatch.await();
    System.out.println(blockedQueue.size);
  }

  class BlockedQueue<T> {
    private final ReentrantLock lock = new ReentrantLock();
    //条件变量：队列不满
    private final Condition notFull = lock.newCondition();
    //条件变量：队列不空
    private final Condition notEmpty = lock.newCondition();
    private Queue<T> queue = new ConcurrentLinkedQueue();
    private int size = 0;

    /**
     * 入队
     *
     * @param x
     */
    public void enq(T x) throws InterruptedException {
      lock.lock();
      try {
        while (size >= 5) {
          //等待队列不满
          notFull.await();
        }
        queue.add(x);
        size++;
        //入队后可通知出队
        notEmpty.signalAll();
      } finally {
        lock.unlock();
      }
    }

    /**
     * 出队
     */
    public void deq() throws InterruptedException {
      lock.lock();
      try {
        while (size == 0) {
          //等待队列不空
          notEmpty.await();
        }
        queue.poll();
        size--;
        //出队后可通知入队
        notFull.signal();
      } finally {
        lock.unlock();
      }
    }

  }
}
