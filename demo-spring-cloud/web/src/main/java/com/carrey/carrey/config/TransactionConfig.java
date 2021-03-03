package com.carrey.carrey.config;

import com.carrey.carrey.transaction.IUserService;
import com.carrey.carrey.transaction.UserServiceImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Conway
 * @className transactionConfig
 * @description
 * @date 2020/11/20 4:03 下午
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
@EnableTransactionManagement
@ComponentScan("com.carrey.carrey.transaction")
public class TransactionConfig {

    @Bean
    public MysqlDataSource mysqlDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://123.57.34.196/spring-jpa-demo");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        mysqlDataSource.setCharacterEncoding("UTF-8");
        mysqlDataSource.setUseSSL(false);
        return mysqlDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(mysqlDataSource());
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager () {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(mysqlDataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    public IUserService userServiceImpl() {
        return new UserServiceImpl();
    }
}
