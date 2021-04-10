package com.carrey.demozookeeper;

import com.carrey.demozookeeper.lock.lock.Lock;
import com.carrey.demozookeeper.lock.lock.ZookeeperLock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class MyLockTest {


    @Test
    public void test() throws InterruptedException {
        Account a = new Account(3000, 1);
        Account b = new Account(2000, 2);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 50; i++) {
            Thread thread1 = new Thread(() -> {
                try {
                    a.transfer(b, 2);
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
            thread2.start();
            thread1.start();
        }
        countDownLatch.await();
        System.out.println("a balance:" + a.getBanalce());
        System.out.println("b balance:" + b.getBanalce());
        System.out.println("---------------------------");
    }

    /**
     * 账户类
     */
    static class Account {

        private ZookeeperLock zookeeperLock = new ZookeeperLock();

        private Allocator allocator = Allocator.getInstance();

        public Account(Integer banalce) {
            this.banalce = banalce;
        }

        public Account(int balance, Integer id) {
            this.id = id;
            this.banalce = balance;
        }

        private Integer id;

        private Integer banalce;

        // 转账
        void transfer(Account target, int amt) throws InterruptedException {
            //List<Account> list = getAccountList(target);
            allocator.apply(zookeeperLock, target.getZookeeperLock());
            // 锁定转出账户
            Lock thisLock = zookeeperLock.writeLock(UUID.randomUUID().toString(), 60 * 1000);
            try {
                Thread.sleep(200);
                // 锁定转入账户
                Lock targetLock = target.getZookeeperLock().writeLock(UUID.randomUUID().toString(), 60 * 1000);
                try {
                    if (this.banalce > amt) {
                        this.banalce -= amt;
                        target.banalce += amt;
                    }
                } finally {
                    target.getZookeeperLock().unlock(targetLock);
                }
            } finally {
                zookeeperLock.unlock(thisLock);
                allocator.release(zookeeperLock, target.getZookeeperLock());
            }
        }


        public Integer getBanalce() {
            return banalce;
        }

        public void setBanalce(Integer banalce) {
            this.banalce = banalce;
        }

        public ZookeeperLock getZookeeperLock() {
            return zookeeperLock;
        }
    }

    /**
     * 单例锁类
     */
    static class Allocator {

        private Allocator() {
        }

        private List<ZookeeperLock> locks = new ArrayList<>();

        public synchronized void apply(ZookeeperLock src, ZookeeperLock tag) {
            while (locks.contains(src) || locks.contains(tag)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            locks.add(src);
            locks.add(tag);
        }

        public synchronized void release(ZookeeperLock src, ZookeeperLock tag) {
            locks.remove(src);
            locks.remove(tag);
            this.notifyAll();
        }

        public static Allocator getInstance() {
            return AllocatorSingle.install;
        }

        static class AllocatorSingle {
            public static Allocator install = new Allocator();
        }
    }
}
