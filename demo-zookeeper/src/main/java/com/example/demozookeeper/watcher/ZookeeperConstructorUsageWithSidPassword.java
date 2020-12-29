package com.example.demozookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Conway
 * @className ZookeeperConstructorUsageWithSidPasswd
 * @description
 * @date 2020/12/29 下午1:47
 */
public class ZookeeperConstructorUsageWithSidPassword implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
            ZooKeeper zooKeeper = new ZooKeeper("123.57.34.196:2181",5000,
                    new ZookeeperConstructorUsageWithSidPassword());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();

        //使用用户 非法 sessionId 和 sessionPassWd
        zooKeeper = new ZooKeeper("123.57.34.196:2181",
                5000,
                new ZookeeperConstructorUsageWithSidPassword(),
                1L,
                "test".getBytes());

        //使用用户 正确 sessionId 和 sessionPassWd
        zooKeeper = new ZooKeeper("123.57.34.196:2181",
                5000,
                new ZookeeperConstructorUsageWithSidPassword(),
                sessionId,
                sessionPasswd);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
