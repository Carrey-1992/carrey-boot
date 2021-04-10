package com.carrey.carrey.设计模式.观察者模式.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

public class EventBusTest {
    @Test
    public void test() {
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();

        EventBus eventBus = new EventBus();
        eventBus.register(observerA);
        eventBus.register(observerB);

        eventBus.post(new EventA("事件A"));
        eventBus.post(new EventB("事件B"));
        eventBus.post(new EventC("事件C"));
    }

    @Test
    public void asyncTest() {
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();

        EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
        eventBus.register(observerA);
        eventBus.register(observerB);

        eventBus.post(new EventA("事件A"));
        eventBus.post(new EventB("事件B"));
        eventBus.post(new EventC("事件C"));

        try {
            //让主线程比子线程执行的慢
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
