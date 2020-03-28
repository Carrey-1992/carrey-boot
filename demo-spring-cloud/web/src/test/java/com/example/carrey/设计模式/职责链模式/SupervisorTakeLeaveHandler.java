package com.example.carrey.设计模式.职责链模式;

/**
 * 主管处理请假申请类
 */
public class SupervisorTakeLeaveHandler extends TakeLeaveHandler {
    @Override
    protected boolean doTakeLeaveHandle(double day) {
        System.out.println("我是你的主管");
        if (day <= 1) {
            System.out.println("作为主管我有权限批假，你的假我批了，撒欢玩去吧!");
            return true;
        }
        System.out.println("作为主管我没有权限批假，要上级领导批准...");
        return false;
    }
}
