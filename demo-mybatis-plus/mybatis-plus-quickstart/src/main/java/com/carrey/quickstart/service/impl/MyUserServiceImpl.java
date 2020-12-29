package com.carrey.quickstart.service.impl;

import com.carrey.quickstart.anno.MyService;
import com.carrey.quickstart.entity.User;
import com.carrey.quickstart.service.IUserService;

/**
 * @author Conway
 * @className MyUserServiceImpl
 * @description
 * @date 2020/12/8 下午5:58
 */
@MyService("myUserService")
public class MyUserServiceImpl implements IUserService {
    @Override
    public User getUser(User user) {
        System.out.println("自定义@MyService");
        return user;
    }
}
