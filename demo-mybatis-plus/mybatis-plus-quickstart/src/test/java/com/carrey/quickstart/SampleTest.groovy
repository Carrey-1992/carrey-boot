package com.carrey.quickstart

import com.carrey.quickstart.entity.User
import com.carrey.quickstart.mapper.UserMapper
import org.assertj.core.util.Lists
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner.class)
@SpringBootTest
class SampleTest {

    @Autowired
    private UserMapper userMapper

    @Test
    @Transactional
    @Rollback
    void testSelect() {
        println "----- selectAll method test ------"
        User user = new User()
        user.setId 5
        user.setName 'Billie'
        user.setAge 24
        user = userMapper.getInfo  user
        Assert.assertEquals "Billie", user.getName()
        println user
    }

    @Test
    @Transactional
    @Rollback
    void testInsert() {
        println "----- insertAll method test ------"
        User user = new User()
        user.setId 6
        user.setName 'Carrey'
        user.setAge 24
        user.setEmail 'test6@baomidou.com'
        def id = userMapper.createUser(user)
        Assert.assertEquals 1,id
        println id
    }

    @Test
    @Transactional
    @Rollback
    void testUpdateById() {
        println "----- updateAll method test ------"
        User user = new User()
        user.setId 5
        user.setName 'Carrey'
        user.setAge 24
        user.setEmail 'test6@baomidou.com'
        def index = userMapper.updateById(user)
        Assert.assertEquals 1,index
        println index
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteById() {
        println "----- updateAll method test ------"
        def index = userMapper.deleteById(1L)
        Assert.assertEquals 1,index
        println index
    }

    @Test
    @Transactional
    @Rollback
    void testSelectByIds() {
        println "----- selectByIds method test ------"
        def userList = userMapper.selectByIds(Lists.newArrayList(1L,2L))
        Assert.assertEquals 2,userList.size()
        userList.each {print it}
    }

    @Test
    @Transactional
    @Rollback
    void testInsetList() {
        def userList = []
        5.times {
            User user = new User()
            user.setId it + 6L
            user.setName 'Carrey'+ (it + 6)
            user.setAge 24
            user.setEmail 'test'+(it + 6)+'@baomidou.com'
            userList.add(user)
        }
        def number = userMapper.insertList(userList)
        Assert.assertEquals(5,number)
        println number
    }
}
