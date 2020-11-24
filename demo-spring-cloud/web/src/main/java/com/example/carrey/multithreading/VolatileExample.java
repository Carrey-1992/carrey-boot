package com.example.carrey.multithreading;

/**
 * @author Conway
 * @className VolatileExample
 * @description
 * @date 2020/8/4 1:57 下午
 */

// 以下代码来源于【参考1】
class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v == true) {
            // 这里x会是多少呢？
        }
    }
}