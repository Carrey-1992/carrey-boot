package com.carrey.quickstart;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Properties;

@Intercepts(
        {@Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class,Object.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class,Object.class,
                        RowBounds.class, ResultHandler.class
                }
        )}
)
public class MyExecutorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        Object result = invocation.proceed();
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
