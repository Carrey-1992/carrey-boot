package com.example.carrey.设计模式.职责链模式;

/**
 * 经理处理请假申请类
 */
public class ManagerTakeLeaveHandler extends TakeLeaveHandler {
    @Override
    protected boolean doTakeLeaveHandle(double day) {
        System.out.println("我是你的经理");
        if (day > 1 && day <= 3) {
            System.out.println("作为经理我有权限批假，你的假我批了，撒欢玩去吧!");
            return true;
        }
        System.out.println("作为经理我没有权限批假，要上级领导批准...");
        return false;
    }
}
