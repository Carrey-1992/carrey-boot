package com.carrey.quickstart

import com.carrey.quickstart.mapper.UserMapper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest
class SampleTest {

    @Autowired
    private UserMapper userMapper

    @Test
    void testSelect() {
        println "----- selectAll method test ------"
        def user = userMapper.getInfo  1
        Assert.assertEquals "Jone", user.getName()
        println user
    }
}
