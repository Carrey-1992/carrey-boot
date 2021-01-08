package com.example.demozookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author Conway
 * @className DelDataSample
 * @description
 * @date 2021/1/4 下午3:03
 */
public class DelDataSample {

    static String path = "/zk-book/c1";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("123.57.34.196:2181,123.57.34.196:2182,123.57.34.196:2183")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());
        Stat stat = new Stat();
        //读取一个节点的数据内容，同时获取到该节点的stat
        //Curator通过传入一个旧的stat变量的方式来存储服务端返回的最新的节点状态信息。
        client.getData().storingStatIn(stat).forPath(path);
        client.delete().deletingChildrenIfNeeded()
                .withVersion(stat.getVersion()).forPath(path);
    }
}
