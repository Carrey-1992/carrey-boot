package com.carrey.quickstart.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * @author Conway
 * @className LongTimeHandler
 * @description
 * @date 2020/11/24 3:21 下午
 */
@MappedJdbcTypes(JdbcType.TIMESTAMP)
@MappedTypes(Long.class)
public class LongTimeHandler extends BaseTypeHandler<Long> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long aLong, JdbcType jdbcType) throws SQLException {
        ps.setDate(i, new Date(aLong));
    }

    @Override
    public Long getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getDate(s).getTime();
    }

    @Override
    public Long getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i).getTime();
    }

    @Override
    public Long getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i).getTime();
    }
}
