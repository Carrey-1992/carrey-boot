package com.example.demozookeeper.watcher;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author Conway
 * @className ZookeeperCreateAPISyncUsage
 * @description
 * @date 2020/12/29 下午2:06
 */
public class ZookeeperCreateAPISyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("123.57.34.196:2181",5000,
                new ZookeeperCreateAPISyncUsage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();

        String path1 = zooKeeper.create("/zk-test-ephemeral-1",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("Success create znode:" + path1);

        String path2 = zooKeeper.create("/zk-test-ephemeral-2",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("Success create znode:" + path2);

    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
