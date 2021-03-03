package com.carrey.demozookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author Conway
 * @className DeleteNodeSample
 * @description
 * @date 2021/1/4 下午2:15
 */
public class DeleteNodeSample {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("123.57.34.196:2181",5000);
        boolean delete = zkClient.deleteRecursive("/carrey-lock1");
        System.out.println("是否删除成功：" + delete);
    }
}
