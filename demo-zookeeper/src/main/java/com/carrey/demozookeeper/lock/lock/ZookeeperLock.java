package com.carrey.demozookeeper.lock.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tommy
 * @author Carrey
 * Created by Tommy on 2019/9/23
 **/
public class ZookeeperLock {

    private ZkClient zkClient;

    private static final String rootPath = "/carrey-lock";

    public ZookeeperLock() {
        zkClient = new ZkClient("123.57.34.196:2181,123.57.34.196:2182,123.57.34.196:2183", 5000, 20000);
        buildRoot();
    }

    // 构建根节点
    public void buildRoot() {
        if (!zkClient.exists(rootPath)) {
            zkClient.createPersistent(rootPath);
        }
    }

    // 获取锁
    public Lock writeLock(String lockId, long timeout) {
        // 创建临时节点
        Lock lockNode = createWriteLockNode(lockId);
        // 尝试激活锁
        lockNode = tryActiveWriteLock(lockNode);
        if (!lockNode.isActive()) {
            try {
                synchronized (lockNode) {
                    // 线程锁住
                    lockNode.wait(timeout);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!lockNode.isActive()) {
            throw new RuntimeException(" lock  timeout");
        }
        return lockNode;
    }

    // 获取锁
    public Lock readLock(String lockId, long timeout) {
        // 创建临时节点
        Lock lockNode = createReadLockNode(lockId);
        // 尝试激活锁
        lockNode = tryActiveReadLock(lockNode);
        if (!lockNode.isActive()) {
            try {
                synchronized (lockNode) {
                    // 线程锁住
                    lockNode.wait(timeout);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!lockNode.isActive()) {
            throw new RuntimeException(" lock  timeout");
        }
        return lockNode;
    }

    // 释放锁
    public void unlock(Lock lock) {
        if (lock.isActive()) {
            zkClient.delete(lock.getPath());
            String lockPath = lock.getLockPath();
            List<String> childrenList = zkClient.getChildren(lockPath);
            if (CollectionUtils.isEmpty(childrenList)) {
                zkClient.delete(lockPath);
            }
        }
    }

    // 尝试激活锁
    private Lock tryActiveWriteLock(Lock lockNode) {

        // 获取根节点下面所有的子节点
        List<String> list = zkClient.getChildren(lockNode.getLockPath())
                .stream()
                .sorted()
                .map(p -> lockNode.getLockPath() + "/" + p)
                .collect(Collectors.toList());

        String firstNodePath = list.get(0);
        //  判断当前是否为最小节点
        if (firstNodePath.equals(lockNode.getPath())) {
            lockNode.setActive(true);
        } else {
            //读请求向比自己序号小的最后一个请求节点注册Watcher监听（防止羊群效应）
            String upNodePath = list.get(list.indexOf(lockNode.getPath()) - 1);
            zkClient.subscribeDataChanges(upNodePath, new IZkDataListener() {
                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {

                }

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    // 事件处理 与心跳 在同一个线程，如果Debug时占用太多时间，将导致本节点被删除，从而影响锁逻辑。
                    System.out.println("节点删除:" + dataPath);
                     Lock lock = tryActiveWriteLock(lockNode);
                    synchronized (lockNode) {
                        if (lock.isActive()) {
                            lockNode.notify(); // 释放了
                        }
                    }
                    zkClient.unsubscribeDataChanges(upNodePath, this);
                }
            });
        }
        return lockNode;
    }

    // 尝试激活锁
    private Lock tryActiveReadLock(Lock lockNode) {

        // 获取根节点下面所有的子节点
        List<String> list = zkClient.getChildren(lockNode.getLockPath())
                .stream()
                .sorted()
                .map(p -> lockNode.getLockPath() + "/" + p)
                .collect(Collectors.toList());

        String firstNodePath = list.get(0);
        //  判断当前是否为最小节点
        if (firstNodePath.equals(lockNode.getPath())) {
            lockNode.setActive(true);
        } else {
            //读请求向比自己序号小的最后一个写请求节点注册Watcher监听
            List<String> wPathList = list.stream()
                    .skip(list.indexOf(lockNode.getPath()) - 1)
                    .filter(nodePath -> StringUtils.equals("w", zkClient.readData(nodePath)))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(wPathList)) {
                lockNode.setActive(true);
            } else {
                //读请求向比自己序号小的最后一个写请求节点注册Watcher监听（防止羊群效应）
                String upNodePath = list.get(0);
                zkClient.subscribeDataChanges(upNodePath, new IZkDataListener() {
                    @Override
                    public void handleDataChange(String dataPath, Object data) throws Exception {

                    }

                    @Override
                    public void handleDataDeleted(String dataPath) throws Exception {
                        // 事件处理 与心跳 在同一个线程，如果Debug时占用太多时间，将导致本节点被删除，从而影响锁逻辑。
                        System.out.println("节点删除:" + dataPath);
                        Lock lock = tryActiveReadLock(lockNode);
                        synchronized (lockNode) {
                            if (lock.isActive()) {
                                lockNode.notify(); // 释放了
                            }
                        }
                        zkClient.unsubscribeDataChanges(upNodePath, this);
                    }
                });
            }
        }
        return lockNode;
    }


    private Lock createLockNode(String lockId, String lockType) {
        String path = rootPath + "/" + lockId;
        if (!zkClient.exists(path)) {
            zkClient.createPersistent(path);
        }
        String nodePath = zkClient.createEphemeralSequential(path + "/lock", lockType);
        return new Lock(lockId, path, nodePath);
    }

    private Lock createWriteLockNode(String lockId) {
        return createLockNode(lockId,"w");
    }

    private Lock createReadLockNode(String lockId) {
        return createLockNode(lockId,"r");
    }
}

