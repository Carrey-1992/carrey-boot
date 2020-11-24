package com.example.carrey.spring;

import com.example.carrey.aop.IAopDemoTest;
import com.example.carrey.config.AopConfig;
import com.example.carrey.config.TransactionConfig;
import com.example.carrey.domain.MainConfig;
import com.example.carrey.domain.MyTestBean;
import com.example.carrey.transaction.IUserService;
import com.example.carrey.transaction.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * @author Conway
 * @className BeanFactoryTest
 * @description
 * @date 2020/10/22 2:50 下午
 */
@Slf4j
public class BeanFactoryTest {
    @Test
    public void test() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("META-INFO/beanFactoryTest.xml"));
        MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
        Assert.assertEquals("testStr",bean.getTestStr());
    }

    @Test
    public void test2() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("META-INFO/beanFactoryTest.xml");
        MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
        Assert.assertEquals("testStr",bean.getTestStr());
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
//        MyTestApi bean = applicationContext.getBean(MyTestApi.class);
//        Assert.assertEquals("testApi",bean.getTestStr());
    }

    @Test
    public void aopTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        IAopDemoTest bean = applicationContext.getBean(IAopDemoTest.class);
        Assert.assertEquals("demoParam",bean.testMethod("demoParam"));
    }

    @Test
    public void transactionTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);
        IUserService userService = applicationContext.getBean(IUserService.class);
        User user = new User();
        user.setName("王康平");
        user.setAge(22);
        user.setAddress("天津市蓟县杨津庄镇小扈驾庄村2区2排5号");
        try {
            userService.testSave(user);
        } catch (Exception e) {
            log.error("模拟异常",e);
        }
        List<User> users = userService.testQueryUserList();
        Assert.assertEquals(users.size(),1);
    }
}
