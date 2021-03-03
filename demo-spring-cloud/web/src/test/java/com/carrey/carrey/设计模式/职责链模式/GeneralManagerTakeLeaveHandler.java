package com.carrey.carrey.设计模式.职责链模式;

/**
 * 总总经理处理请假申请类
 */
public class GeneralManagerTakeLeaveHandler extends TakeLeaveHandler {
    @Override
    protected boolean doTakeLeaveHandle(double day) {
        System.out.println("我是你的总经理");
        if (day > 3 && day <= 10) {
            System.out.println("作为总经理我有权限批假，你的假我批了，撒欢玩去吧!");
            return true;
        }
        System.out.println("作为总经理我没有权限批假，你请的假有点多了，谁都批不了，老实上班吧!");
        return false;
    }
}
