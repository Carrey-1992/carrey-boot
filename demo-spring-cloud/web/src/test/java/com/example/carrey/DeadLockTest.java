package com.example.carrey;

import com.baomidou.mybatisplus.extension.api.R;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Carrey
 * @version 0.0.1
 * @description DeadLockTest
 * @create 2019-10-25 10:33
 */
public class DeadLockTest {
  @Test
  public void test() throws InterruptedException {
    Account a = new Account(30000, 1);
    Account b = new Account(20000, 2);
    CountDownLatch countDownLatch = new CountDownLatch(10000);
    for (int i = 0; i < 5000; i++) {
      Thread thread1 = new Thread(() -> {
        try {
          a.transfer2(b, 2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
          countDownLatch.countDown();
      });

      Thread thread2 = new Thread(() -> {
        try {
          b.transfer2(a, 1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
          countDownLatch.countDown();
      });
      thread1.start();
      thread2.start();
    }
      countDownLatch.await();
    System.out.println("a balance:" + a.getBalance());
    System.out.println("b balance:" + b.getBalance());
    System.out.println("---------------------------");
  }

  class Account {

    private Allocator allocator = Allocator.getInstance();

    private Integer id;

    private int balance;

    private final ReentrantLock reentrantLock = new ReentrantLock(true);

    // 转账
    void transfer(Account target, int amt) throws InterruptedException {
      //List<Account> list = getAccountList(target);
      Thread.sleep(1);
      allocator.apply(this, target);
      try {
        // 锁定转出账户
        synchronized (this) {
          // 锁定转入账户
          synchronized (target) {
            if (this.balance > amt) {
              this.balance -= amt;
              target.balance += amt;
            }
          }
        }
      } finally {
        allocator.free(this, target);
      }
    }

    // 转账
    void transfer2(Account target, int amt) throws InterruptedException {
      //List<Account> list = getAccountList(target);
      Thread.sleep(1);
      while (true) {
        // 锁定转出账户
        if (this.reentrantLock.tryLock()) {
          try{
            // 锁定转入账户
            if (target.reentrantLock.tryLock()) {
              try{
                this.balance -= amt;
                target.balance += amt;
                break;
              }finally {
                target.reentrantLock.unlock();
              }
            }
          }finally {
            this.reentrantLock.unlock();
          }
        }
      }
    }

    private List<Account> getAccountList(Account target) {
      List<Account> list = Lists.newArrayList();
      list.add(target);
      list.add(this);
      list.sort(Comparator.comparing(Account::getId));
      return list;
    }

    public Account(int balance) {
      this.balance = balance;
    }

    public int getBalance() {
      return balance;
    }

    public Account(int balance, Integer id) {
      this.id = id;
      this.balance = balance;
    }

    public Integer getId() {
      return id;
    }

    public void setAllocator(Allocator allocator) {
      this.allocator = allocator;
    }
  }


  /**
   * 这是个范式写法，请注意。
   */
  static class Allocator {

    private List<Object> list = Lists.newArrayList();

    private static boolean flag = true;

    synchronized void apply(Object from, Object to) {
      if (list.contains(from) || list.contains(to)) {
          try {
              this.wait();
          } catch (InterruptedException e) {
          }
      }
      list.add(from);
      list.add(to);
    }

    synchronized void free(Object from, Object to) {
      list.remove(from);
      list.remove(to);
      this.notifyAll();
    }

    private Allocator() {
      if (flag) {
        flag = false;
        return;
      }
      throw new AssertionError("单例模式被侵犯！");
    }

    static class AllocatorHandler {
      private static Allocator instance = new Allocator();
    }

    public static Allocator getInstance() {
      return AllocatorHandler.instance;
    }
  }

}


