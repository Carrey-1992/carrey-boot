package com.example.demozookeeper.watcher;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author Conway
 * @className ZookeeperCreateAPIASyncUsage
 * @description
 * @date 2020/12/29 下午3:13
 */
public class ZookeeperCreateAPIASyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper("123.57.34.196:2181",5000,
                new ZookeeperCreateAPIASyncUsage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await();

        zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),"I am context");

        zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),"I am context");

        zooKeeper.create("/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(),"I am context");

        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }

    static class IStringCallback implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            System.out.println("Create path result:[" + i + ", " + s + "," + o + ", real path name:" + s1);
        }
    }
}
