package com.carrey.quickstart.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.carrey.quickstart.entity.User;

/**
 * @author Conway
 * @className MyUserMapper
 * @description
 * @date 2021/1/5 下午8:27
 */
public interface MyUserMapper {

    /**
     * 根据ID获取对象.
     * @param user
     * @return
     */
    User getInfo(User user);

}
