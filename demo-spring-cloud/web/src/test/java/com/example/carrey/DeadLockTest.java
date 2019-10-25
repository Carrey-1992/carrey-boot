package com.example.carrey;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
    CountDownLatch countDownLatch = new CountDownLatch(1000);
    for (int i = 0; i < 500; i++) {
      Thread thread1 = new Thread(() -> {
        try {
          a.transfer(b, 1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
          countDownLatch.countDown();
      });

      Thread thread2 = new Thread(() -> {
        try {
          b.transfer(a, 1);
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


  static class Allocator {

    private List<Object> list = Lists.newArrayList();

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
    }

    static class AllocatorHandler {
      private static Allocator instance = new Allocator();
    }

    public static Allocator getInstance() {
      return AllocatorHandler.instance;
    }
  }

}


