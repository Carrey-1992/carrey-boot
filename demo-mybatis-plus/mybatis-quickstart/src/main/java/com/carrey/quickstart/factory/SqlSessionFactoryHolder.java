package com.carrey.quickstart.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Conway
 * @className SqlSessionFactoryHolder
 * @description
 * @date 2020/11/24 11:34 上午
 */
public class SqlSessionFactoryHolder {

    /**
     * 获取SqlSessionFactory
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return SqlSessionFactoryHandler.getSingleton();
    }

    private static class SqlSessionFactoryHandler {

        private static SqlSessionFactory sqlSessionFactory;

        public static SqlSessionFactory getSingleton() {
            if (Objects.isNull(sqlSessionFactory)) {
                synchronized (SqlSessionFactoryHolder.class) {
                    if (Objects.isNull(sqlSessionFactory)) {
                        String resource = "mybatis-config.xml";
                        InputStream inputStream = null;
                        try {
                            inputStream = Resources.getResourceAsStream(resource);
                        } catch (IOException e) {
                            throw new RuntimeException("读取mybatis-config.xml文件失败！");
                        }
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    }
                }
            }
            return sqlSessionFactory;
        }
    }
}
