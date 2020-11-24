package com.carrey.quickstart.sevice.impl;

import com.carrey.quickstart.dao.UserMapper;
import com.carrey.quickstart.factory.SqlSessionFactoryHolder;
import com.carrey.quickstart.po.User;
import com.carrey.quickstart.sevice.IUserService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Conway
 * @className UserServiceImpl
 * @description
 * @date 2020/11/24 11:51 上午
 */
public class UserServiceImpl implements IUserService {

    @Override
    public User getInfo(User user) {
        UserMapper mapper;
        try (SqlSession sqlSession = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
            mapper = sqlSession.getMapper(UserMapper.class);
        }
        return mapper.getInfo(user);
    }

    @Override
    public Integer createUser(User user) {
        return null;
    }

    @Override
    public int updateById(User user) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public List<User> selectByIds(List<Long> ids) {
        return null;
    }

    @Override
    public int insertList(List<User> userList) {
        return 0;
    }
}
