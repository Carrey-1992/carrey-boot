package com.carrey.carrey.spring;

import com.carrey.carrey.aop.IAopDemoTest;
import com.carrey.carrey.config.AopConfig;
import com.carrey.carrey.config.TransactionConfig;
import com.carrey.carrey.domain.bean.AnyStrategy;
import com.carrey.carrey.domain.MainConfig;
import com.carrey.carrey.domain.MyTestBean;
import com.carrey.carrey.transaction.IUserService;
import com.carrey.carrey.transaction.User;
import com.carrey.carrey.utils.EnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
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

//    @Test
//    public void decorateTest() {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DecorateConfig.class);
//        FilterProccess filterProccess = (FilterProccess)applicationContext.getBean("decorateContext");
//        filterProccess.filter(Lists.newArrayList("1","2","3"));
//    }

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

    @Test
    public void testStrategy() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String code = "a";
        AnyStrategy anyStrategy = EnumUtils.getEnumByStrCode(AnyStrategy.class, code);
        int count = anyStrategy.getCount();
        List<Object> list = anyStrategy.getList();
        System.out.println(list);
    }

}
