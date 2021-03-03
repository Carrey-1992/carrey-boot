package com.carrey.carrey.设计模式.职责链模式;

import java.util.Objects;

/**
 * 请假处理抽象父类
 */
public abstract class TakeLeaveHandler {

    private TakeLeaveHandler successor;

    public void setSuccessor(TakeLeaveHandler successor) {
        this.successor = successor;
    }

    public boolean takeLeaveHandle(double day) {
        boolean flag = doTakeLeaveHandle(day);
        if (!flag && Objects.nonNull(successor)){
            return successor.takeLeaveHandle(day);
        }
        return flag;
    }

    /**
     * 请假
     * @param day
     * @return
     */
    protected abstract boolean doTakeLeaveHandle(double day);
}
