package com.carrey.quickstart.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.carrey.quickstart.anno.MyService;
import com.carrey.quickstart.entity.User;
import com.carrey.quickstart.mapper.MyUserMapper;
import com.carrey.quickstart.mapper.UserMapper;
import com.carrey.quickstart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Conway
 * @className MyUserServiceImpl
 * @description
 * @date 2020/12/8 下午5:58
 */
@DS("spring-jpa-demo")
@MyService("myUserService")
public class MyUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyUserMapper myUserMapper;

    @Override
    public User getUser(User user) {
        System.out.println("自定义@MyService");
        return user;
    }

    @Transactional(rollbackFor = {Error.class,Exception.class})
    @Override
    public void dynamic() {
        myUserMapper.getInfo(new User());
    }
}
