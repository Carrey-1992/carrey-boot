package com.carrey.carrey.并发编程;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Carrey
 * @version 0.0.1
 * @description ReadWriteLockTest
 * @create 2019-11-11 17:29
 */
public class ReadWriteLockTest {

  class Cache<K,V> {
    private Map<K,V> kvMap = Maps.newHashMap();
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();

    //读缓存
    public V get(K k) {
      readLock.lock();
      try {
        return kvMap.get(k);
      }finally {
        readLock.unlock();
      }
    }

    //写缓存
    public V put(K k,V v) {
      writeLock.lock();
      try {
        return kvMap.put(k,v);
      }finally {
        writeLock.unlock();
      }
    }
  }
}
