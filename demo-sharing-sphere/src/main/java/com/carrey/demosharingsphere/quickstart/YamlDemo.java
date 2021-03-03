package com.carrey.demosharingsphere.quickstart;

import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Conway
 * @className YamlDemo
 * @description
 * @date 2021/3/2 7:50 下午
 */
public class YamlDemo {
    public static void main(String[] args) throws IOException, SQLException {
        //获取数据源对象
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File("demo-sharing-sphere/src/main/resources/yamlFile.yml"));
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
