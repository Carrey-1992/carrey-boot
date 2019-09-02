package com.carrey.quickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carrey.quickstart.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
  User getInfo(@Param("id") Integer id);
}
