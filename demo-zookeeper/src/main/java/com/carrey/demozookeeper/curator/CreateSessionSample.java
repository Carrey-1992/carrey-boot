package com.carrey.demozookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author Conway
 * @className CreateSessionSample
 * @description
 * @date 2021/1/4 下午2:24
 */
public class CreateSessionSample {

    public static void main(String[] args) throws Exception{
        /**
         * ExponentialBackoffRetry的重试策略设计如下。
         * 给定一个初始化sleep时间baseSleepTimeMs，在这个基础上结合重试次数，通过以下公式计算出当前需要sleep的时间：
         * 当前sleep时间 = baseSleepTimeMs * Math.max(1,random.nextInt(1 << (retryCount + 1)))
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("123.57.34.196:2181,123.57.34.196:2182,123.57.34.196:2183",
                5000,
                3000,
                retryPolicy);
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
