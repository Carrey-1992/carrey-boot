package com.example.carrey.设计模式.观察者模式.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 观察者A
 */
public class ObserverA {

    @Subscribe
    public void handleMessage(EventA eventA) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ObserverA："+ eventA.getMessage());
    }
}
