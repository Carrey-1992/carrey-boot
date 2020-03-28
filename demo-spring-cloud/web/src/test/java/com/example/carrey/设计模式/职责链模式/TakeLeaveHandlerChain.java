package com.example.carrey.设计模式.职责链模式;

import java.util.Objects;

/**
 * 请假处理责任链对象
 */
public class TakeLeaveHandlerChain {

    private TakeLeaveHandler head = null;
    private TakeLeaveHandler tail = null;

    public TakeLeaveHandlerChain setHandler(TakeLeaveHandler handler) {
        handler.setSuccessor(null);
        if (Objects.isNull(head)) {
            head = handler;
            tail = handler;
            return this;
        }

        tail.setSuccessor(handler);
        tail = handler;
        return this;
    }

    public boolean doHandle(double day) {
        System.out.println("哦！你想请" + day + "天假啊...");
        if (Objects.nonNull(head)) {
            return head.takeLeaveHandle(day);
        }
        return false;
    }

}
