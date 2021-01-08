package com.carrey.quickstart.service;

import com.carrey.quickstart.entity.User;

public interface IUserService {
    /**
     * 获取user
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 多数据源测试
     */
    void dynamic();
}
