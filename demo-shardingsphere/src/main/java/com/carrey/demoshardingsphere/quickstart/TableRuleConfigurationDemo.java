package com.carrey.demoshardingsphere.quickstart;


import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
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

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 配置分库
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 3}"));
        shardingRuleConfig.getBindingTableGroups().add("t_order,t_order_item");
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());

        // 配置分表策略 对表分片是采用我们order_id
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(
//                new StandardShardingStrategyConfiguration("order_id",
//                        new PreciseShardingAlgorithm<Long>() {
//                            @Override
//                            public String doSharding(Collection<String> collection, final PreciseShardingValue<Long> preciseShardingValue) {
//                                for (String each : collection) {
//                                    if (each.endsWith(preciseShardingValue.getValue() % 3 + "")) {//这句话会产生什么？只会产生偶数的订单
//                                        return each;
//                                    }
//                                }
//                                throw new UnsupportedOperationException();
//                            }
//                        }));

        // 获取数据源对象
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        //获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(), properties);
//        selectRange(dataSource);
        insertData(dataSource);
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        // 配置order_item表规则
        // 配置Order表规则
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds${0..2}.t_order_item${0..2}");

        // 分表策略
        orderItemTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("order_id", "t_order_item${order_id % 3}"));
        return orderItemTableRuleConfig;
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds${0..2}.t_order${0..2}");
        // 配置雪花算法
        orderTableRuleConfig.setKeyGenerator(new DefaultKeyGenerator());
        orderTableRuleConfig.setKeyGeneratorColumnName("order_id");

        // 分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 3}"));
        return orderTableRuleConfig;
    }

    public static void insertData(DataSource dataSource) throws SQLException {
        for (int i = 1; i < 10; i++) {
            long orderId = executeAndGetGeneratedKey(dataSource, "INSERT INTO t_order (user_id) VALUES (10)");
            execute(dataSource, String.format("INSERT INTO t_order_item (order_id, user_id) VALUES (%d, 10)", orderId));
            orderId = executeAndGetGeneratedKey(dataSource, "INSERT INTO t_order (user_id) VALUES (11)");
            execute(dataSource, String.format("INSERT INTO t_order_item (order_id, user_id) VALUES (%d, 11)", orderId));
            orderId = executeAndGetGeneratedKey(dataSource, "INSERT INTO t_order (user_id) VALUES (12)");
            execute(dataSource, String.format("INSERT INTO t_order_item (order_id, user_id) VALUES (%d, 12)", orderId));
        }
    }

    private static void execute(final DataSource dataSource, final String sql) throws SQLException {
        try (
                Connection conn = dataSource.getConnection();
                Statement statement = conn.createStatement()) {
            statement.execute(sql);
        }
    }

    private static long executeAndGetGeneratedKey(final DataSource dataSource, final String sql) throws SQLException {
        long result = -1;
        try (
                Connection conn = dataSource.getConnection();
                Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        }
        return result;
    }

    private static void selectRange(DataSource dataSource) throws SQLException {
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
