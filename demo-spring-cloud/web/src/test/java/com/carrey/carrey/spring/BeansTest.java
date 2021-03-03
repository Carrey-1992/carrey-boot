package com.carrey.carrey.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.IntrospectionException;
import java.util.Map;

/**
 * @author Conway
 * @className BeansTest
 * @description
 * @date 2020/8/15 6:27 下午
 */
@Configuration
public class BeansTest {

    public static void main(String[] args) throws IntrospectionException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //
        applicationContext.register(BeansTest.class);
        applicationContext.refresh();
        lookUpBeansProperty(applicationContext);

        //1、通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        beanDefinitionBuilder.addPropertyValue("name","carrey");
        beanDefinitionBuilder.addPropertyValue("age",20);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    }

    public static void lookUpBeansProperty(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,Person> personMap = listableBeanFactory.getBeansOfType(Person.class);
            System.out.println(personMap);
        }
    }

    @Bean
    public Person person() {
        return new Person();
    }


    class Person {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
