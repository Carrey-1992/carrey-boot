package com.carrey.demosharingsphere.quickstart;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Conway
 * @className QuickStartDemo
 * @description
 * @date 2021/3/2 3:01 下午
 */
public class TableRuleConfigurationDemo {
    public static void main(String[] args) throws SQLException {
        // 配置真是数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://123.57.34.196:3306/ds0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第一个数据源
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://123.57.34.196:3306/ds1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("ds1", dataSource2);

        // 配置第一个数据源
        BasicDataSource dataSource3 = new BasicDataSource();
        dataSource3.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource3.setUrl("jdbc:mysql://123.57.34.196:3306/ds2");
        dataSource3.setUsername("root");
        dataSource3.setPassword("123456");
        dataSourceMap.put("ds2", dataSource3);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds${0..2}.t_order${0..2}");

        // 分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 3}"));

        // 配置order_item表规则
        // 配置Order表规则
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds${0..2}.t_order_item${0..2}");

        // 分表策略
        orderItemTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("order_id", "t_order_item${order_id % 3}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 配置分库
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 3}"));
        shardingRuleConfig.getBindingTableGroups().add("t_order,t_order_item");
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);

        //获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(), new Properties());
        String sql = "SELECT o.*,i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id where o.user_id =? AND o.order_id =?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 3);
            preparedStatement.setInt(2, 3);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getBigDecimal(4));
                    System.out.println(rs.getString(5));
                    System.out.println(rs.getInt(6));
                    System.out.println(rs.getDate(7));
                    System.out.println(rs.getString(8));
                    System.out.println(rs.getDate(9));
                    System.out.println(rs.getString(10));
                    System.out.println(rs.getInt(11));
                    System.out.println(rs.getInt(12));
                    System.out.println(rs.getInt(13));
                    System.out.println(rs.getString(14));
                    System.out.println(rs.getInt(15));
                    System.out.println("------------------------------");
                }
            }
        }
    }
}
