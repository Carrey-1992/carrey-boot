package com.carrey.quickstart.sevice;

import com.carrey.quickstart.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Conway
 * @className IUserService
 * @description
 * @date 2020/11/24 11:50 上午
 */
public interface IUserService {

    /**
     * 根据ID获取对象.
     * @param user
     * @return
     */
    User getInfo(User user);

    /**
     * 创建User.
     * @param user
     * @return
     */
    Integer createUser(User user);

    /**
     * 通过主键更新.
     * @return
     */
    int updateById(User user);


    /**
     * 通过主键删除信息.
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 通过主键集合获取对象集合.
     * @param ids
     * @return
     */
    List<User> selectByIds(@Param("ids") List<Long> ids);

    /**
     * 批量插入.
     * @param userList
     * @return
     */
    int insertList(List<User> userList);

}
