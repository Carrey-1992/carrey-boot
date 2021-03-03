package com.carrey.carrey.transaction;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Conway
 * @className UserServiceImpl
 * @description
 * @date 2020/11/20 4:38 下午
 */
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional(rollbackFor = {Exception.class, Error.class})
    @Override
    public void testSave(User user) {
        jdbcTemplate.update("INSERT INTO `spring-jpa-demo`.user (name, age, address, created_time, updated_time) " +
                "VALUES (?, ?, ?, ?, ?)", new Object[]{user.getName(), user.getAge(), user.getAddress(), new Date(), new Date()});
        UserServiceImpl userService = (UserServiceImpl)AopContext.currentProxy();
        userService.testSave1();
        throw new RuntimeException("自定义事务回滚异常");
    }

    @Override
    public List<User> testQueryUserList() {
        String sql = "select * from user";
        //        第三个参数可以省略
        List<User> users = jdbcTemplate.query(sql,  new BeanPropertyRowMapper<User>(User.class));
        System.out.println(users);
        return users;
    }

    @Transactional(rollbackFor = {Exception.class, Error.class},propagation = Propagation.REQUIRES_NEW)
    public void testSave1() {
        User user = new User();
        user.setName("张杰1");
        user.setAge(27);
        user.setAddress("天津市蓟县杨津庄镇小扈驾庄村2区2排5号");
        jdbcTemplate.update("INSERT INTO `spring-jpa-demo`.user (name, age, address, created_time, updated_time) " +
                "VALUES (?, ?, ?, ?, ?)", new Object[]{user.getName(), user.getAge(), user.getAddress(), new Date(), new Date()});

    }

}
