package com.example.demozookeeper.original;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author Conway
 * @className ZookeeperGetDataAPISyncUsage
 * @description
 * @date 2020/12/29 下午4:52
 */
public class ZookeeperGetDataAPISyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception{
        String path = "/zk-book—test5";
        zk = new ZooKeeper("123.57.34.196:2181",5000,
                new ZookeeperGetDataAPISyncUsage());
        connectedSemaphore.await();

        zk.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData(path, true, stat)));
        System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
        zk.setData(path,"123".getBytes(),-1);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                //如果获取到的子节点发生变化的话会监听到事件，但是需要主动重新获取
                try {
                    System.out.println(new String(zk.getData(watchedEvent.getPath(), true, stat)));
                    System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
                } catch (Exception e) {

                }
            }
        }
    }
}
