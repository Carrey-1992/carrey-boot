package com.example.demozookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * @author Conway
 * @className ZkLock
 * @description
 * @date 2021/1/7 下午6:05
 */
public class ZkLock {

    static String path = "/zk-lock1";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("123.57.34.196:2181,123.57.34.196:2182,123.57.34.196:2183")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    static CountDownLatch countDownLatch = new CountDownLatch(10);

//    public static void main(String[] args)  {
//        InterProcessLock lock = new InterProcessSemaphoreMutex(client, path);
//        //模拟100个线程抢锁
//        for (int i = 0; i < 100; i++) {
//            new Thread(new TestThread(i, lock)).start();
//        }
//    }
//
//    static class TestThread implements Runnable {
//        private Integer threadFlag;
//        private InterProcessMutex lock;
//
//        public TestThread(Integer threadFlag, InterProcessMutex lock) {
//            this.threadFlag = threadFlag;
//            this.lock = lock;
//        }
//
//        @Override
//        public void run() {
//            try {
//                lock.acquire();
//                System.out.println("第"+threadFlag+"线程获取到了锁");
//                //等到1秒后释放锁
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    lock.release();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
