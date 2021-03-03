package com.carrey.carrey.mybatis;

import com.google.common.collect.Lists;
import com.vip.vjtools.vjkit.reflect.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Intercepts(
        {@Signature(
            //设置拦截的接口：（Executor，ParameterHandler，ResultSetHandler，StatementHandler）
            type = Executor.class,
            //update方法会在所有的INSERT、UPDATE、DELETE执行时被调用
            method = "update",
            //方法的参数类型
            args = {MappedStatement.class,Object.class}
        )}
)
/**
 * Mybatis自定义插件要实现Interceptor拦截接口.
 */
@Slf4j
public class MyExecutorPlugin implements Interceptor {

    /**
     * 用于setProperties()方法进行测试.
     */
    private String test;

    /**
     * 方法拦截
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("properties value is [{}]",test);
        //获取当前被拦截的对象
        //Executor target = (Executor)invocation.getTarget();
        //获取当前被拦截的方法
        Method method = invocation.getMethod();
        final String name = method.getName();
        //返回被拦截方法中的参数
        Object[] args = invocation.getArgs();
        Object obj = args[1];
        if (!(obj instanceof BaseEntity)) {
            return invocation.proceed();
        }
        for (Field field : getFields(obj)) {
            UpdateBy updateBy = field.getAnnotation(UpdateBy.class);
            CreateBy createBy = field.getAnnotation(CreateBy.class);
            UpdateAt updateAt = field.getAnnotation(UpdateAt.class);
            CreateAt createAt = field.getAnnotation(CreateAt.class);
            if (Objects.equals("update",name)) {
                if (Objects.nonNull(updateBy)) {
                    //TODO:从上下文中获取用户id进行set
                    break;
                }
                if(Objects.nonNull(updateAt)){
                    ReflectionUtil.setFieldValue(obj,field.getName(),new Date());
                    break;
                }
            }
            if (Objects.equals("insert",name)) {
                if (Objects.nonNull(createBy)) {
                    //TODO: 从上下文中获取用户id进行set
                }
                if (Objects.nonNull(createAt)) {
                    ReflectionUtil.setFieldValue(obj,field.getName(),new Date());
                    break;
                }

            }
        }
        //proceed()方法实际上执行了method.invoke(target,args)方法，并返回执行结果.
        Object result = invocation.proceed();
        return result;
    }

    /**
     * 获取到拦截的对象,底层也是通过代理实现的，实际上是拿到一个目标代理对象.
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    /**
     * 获取设置的阈值等参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        test = properties.getProperty("test");
    }


    private static List<Field> getFields(Object targetEntity) {
        List<Field> fieldList = Lists.newArrayList();
        Class tempClass = targetEntity.getClass();
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        return fieldList;
    }
}
