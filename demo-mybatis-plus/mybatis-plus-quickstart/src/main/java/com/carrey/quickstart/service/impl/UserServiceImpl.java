package com.carrey.quickstart.service.impl;

import com.carrey.quickstart.entity.User;
import com.carrey.quickstart.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Conway
 * @className UserServiceImpl
 * @description
 * @date 2020/12/8 上午11:14
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User getUser(User user) {
        return user;
    }

    @Override
    public void dynamic() {

    }
}
