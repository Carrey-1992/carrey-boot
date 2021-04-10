package com.carrey.carrey.设计模式.职责链模式;

import org.junit.jupiter.api.Test;

public class TakeLeaveTest {

    @Test
    public void test() {
        System.out.println("小明想试试最多能请几天假");
        boolean flag = true;
        double day = 1.0;
        while (flag) {
            flag = new TakeLeaveHandlerChain()
                    .setHandler(new SupervisorTakeLeaveHandler())
                    .setHandler(new ManagerTakeLeaveHandler())
                    .setHandler(new GeneralManagerTakeLeaveHandler())
                    .doHandle(day);
            if (flag) {
                day++;
            }
            System.out.println("-----------------------------------------------------------");
        }
        System.out.println("小明最多能请"+ (day - 1) +"天假！");
    }

    @Test
    public void test2() {
        System.out.println("小明想试试最多能请几天假");
        boolean flag = true;
        double day = 1.0;
        while (flag) {
            flag = new TakeLeaveHandlerChain2()
                    .setHandler(new SupervisorTakeLeaveHandler())
                    .setHandler(new ManagerTakeLeaveHandler())
                    .setHandler(new GeneralManagerTakeLeaveHandler())
                    .doHandle(day);
            if (flag) {
                day++;
            }
            System.out.println("-----------------------------------------------------------");
        }
        System.out.println("小明最多能请"+ (day - 1) +"天假！");
    }
}
