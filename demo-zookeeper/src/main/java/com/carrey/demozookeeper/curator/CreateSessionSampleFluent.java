package com.carrey.demozookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author Conway
 * @className CreateSessionSampleFluent
 * @description
 * @date 2021/1/4 下午2:36
 */
public class CreateSessionSampleFluent {

    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("123.57.34.196:2181,123.57.34.196:2182,123.57.34.196:2183")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
