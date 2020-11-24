package com.carrey.quickstart;

import com.carrey.quickstart.po.User;
import com.carrey.quickstart.sevice.IUserService;
import com.carrey.quickstart.sevice.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Conway
 * @className MybatisTest
 * @description
 * @date 2020/11/24 12:34 下午
 */
public class MybatisTest {

    @Test
    public void test1() {
        User user = new User();
        user.setName("王康伟");
        IUserService userService = new UserServiceImpl();
        User info = userService.getInfo(user);

        Assert.assertEquals("王康伟",info.getName());
    }

}
