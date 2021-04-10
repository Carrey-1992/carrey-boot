package com.carrey.carrey.并发编程;

import org.junit.jupiter.api.Test;

import java.util.Vector;

/**
 * @author Conway
 * @className WaitNotifyTest
 * @description
 * @date 2020/8/4 11:16 上午
 */
public class WaitNotifyTest {

    @Test
    public void test() {
        Vector<Integer> pool = new Vector<>();
        Producer producer = new Producer(pool,10);
        Consumer consumer = new Consumer(pool);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    /**
     * 生产者
     */
    class Producer implements Runnable {
        private Vector<Integer> pool;

        private Integer size;

        public Producer(Vector<Integer> pool, Integer size) {
            this.pool = pool;
            this.size = size;
        }

        @Override
        public void run() {
            for (; ; ) {
                try {
                    System.out.println("生产一个商品");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void produce(int i) throws InterruptedException {
            while (pool.size() == size) {
                synchronized (pool) {
                    System.out.println("生产者等待消费者消费商品,当前商品数量为" + pool.size());
                    pool.wait();//等待消费者消费
                }
            }
            synchronized (pool) {
                pool.add(i);
                pool.notifyAll();//生产成功，通知消费者消费
            }
        }
    }

    class Consumer implements Runnable {

        private Vector pool;

        public Consumer(Vector pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            for (; ; ) {
                try {
                    System.out.println("消费一个商品");
                    consume();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {
            synchronized (pool) {
                while (pool.isEmpty()) {
                    System.out.println("消费者等待生产者生产商品,当前商品数量为" + pool.size());
                    pool.wait();//等待生产者生产商品
                }
            }
            synchronized (pool) {
                pool.remove(0);
                pool.notifyAll();//通知生产者生产商品
            }
        }
    }
}
