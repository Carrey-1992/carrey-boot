package com.carrey.demoshardingsphere.service;

import com.carrey.demoshardingsphere.po.LogEsbRequestResume;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author carrey
 * @since 2021-03-05
 */
public interface ILogEsbRequestResumeService  {

    /**
     * 根据userId查询集合
     * @param userId
     * @return
     */
    List<LogEsbRequestResume> queryList(String userId);

}
