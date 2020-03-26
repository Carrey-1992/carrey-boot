package com.example.carrey.设计模式.观察者模式.eventbus;

import com.google.common.eventbus.Subscribe;

public class ObserverB {

    @Subscribe
    public void handleMessageB(EventB eventB) {
        System.out.println("ObserverB："+ eventB.getMessage());
    }

    @Subscribe
    public void handleMessageC(EventC eventc) {
        System.out.println("ObserverC："+ eventc.getMessage());
    }
}
