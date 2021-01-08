package com.example.demozookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author Conway
 * @className CreateNodeSample
 * @description
 * @date 2021/1/4 下午2:59
 */
public class CreateNodeSample {

    static String path = "/carrey-lock1";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("123.57.34.196:2181,123.57.34.196:2182,123.57.34.196:2183")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                //递归创建父节点
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());
    }
}
