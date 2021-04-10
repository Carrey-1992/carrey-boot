package com.carrey.carrey.并发编程;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MyLockTest {

  @Test
  public void test() throws InterruptedException {
    Account src = new Account(10000);
    Account target = new Account(10000);
    //CountDownLatch countDownLatch = new CountDownLatch(9999);
    for (int i = 0; i < 5000; i++) {
      new Thread(()->{
        //进行转账
        src.transactionToTarget(1,target);
        //countDownLatch.countDown();
      }).start();

      new Thread(()->{
        target.transactionToTarget(1,src);
      }).start();
    }
    //countDownLatch.await();
    System.out.println("src="+src.getBanalce() );
    System.out.println("target="+target.getBanalce() );
  }

  /**
   * 账户类
   */
  static class Account{
    public Account(Integer banalce) {
      this.banalce = banalce;
    }
    private Integer banalce;

    /**
     * 转账方法
     * @param money
     * @param target
     */
    public void transactionToTarget(Integer money,Account target){
      //获取锁
      Allocator.getInstance().apply(this,target);
      this.banalce -= money;
      target.setBanalce(target.getBanalce()+money);
      //释放锁
      Allocator.getInstance().release(this,target);
    }

    public Integer getBanalce() {
      return banalce;
    }

    public void setBanalce(Integer banalce) {
      this.banalce = banalce;
    }
  }

  /**
   * 单例锁类
   */
  static class Allocator {

    private Allocator(){}

    private List<Account> locks = new ArrayList<>();
    public synchronized void apply(Account src,Account tag){
      while (locks.contains(src)||locks.contains(tag)) {
        try {
          this.wait();
        } catch (InterruptedException e) {
        }
      }
      locks.add(src);
      locks.add(tag);
    }

    public synchronized void release(Account src,Account tag){
      locks.remove(src);
      locks.remove(tag);
      this.notifyAll();
    }

    public static Allocator getInstance(){
      return AllocatorSingle.install;
    }
    static class AllocatorSingle{
      public static Allocator install = new Allocator();
    }
  }
}
