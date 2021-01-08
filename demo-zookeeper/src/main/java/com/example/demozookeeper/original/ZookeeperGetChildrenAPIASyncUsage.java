package com.example.demozookeeper.original;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author Conway
 * @className ZookeeperGetChildrenAPIASyncUsage
 * @description
 * @date 2020/12/29 下午3:48
 */
public class ZookeeperGetChildrenAPIASyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception{
        String path = "/zk-book—test2";
        zk = new ZooKeeper("123.57.34.196:2181",5000,
                new ZookeeperGetChildrenAPIASyncUsage());
        connectedSemaphore.await();

        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.getChildren(path, true, (i, s, o, list, stat) -> {
            System.out.println("Get Children znode result : [response code:"
                    + i + ", param path:" + s + ", children list" + list + ",stat:" + stat);
        },null);
        zk.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create(path+"/c3","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                //如果获取到的子节点发生变化的话会监听到事件，但是需要主动重新获取
                try {
                    System.out.println("ReGetChild:" + zk.getChildren(watchedEvent.getPath(), true));
                } catch (Exception e) {

                }
            }
        }
    }
}
