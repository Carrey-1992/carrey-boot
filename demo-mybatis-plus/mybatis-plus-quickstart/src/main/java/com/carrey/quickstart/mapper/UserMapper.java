package com.carrey.quickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carrey.quickstart.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
  /**
   * 根据ID获取对象.
   * @param id
   * @return
   */
  User getInfo(@Param("id") Integer id);

  /**
   * 创建User.
   * @param user
   * @return
   */
  Integer createUser(User user);
}
