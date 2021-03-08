package com.carrey.demoshardingsphere.service.impl;

import com.carrey.demoshardingsphere.dao.LogEsbRequestResumeMapper;
import com.carrey.demoshardingsphere.po.LogEsbRequestResume;
import com.carrey.demoshardingsphere.po.LogEsbRequestResumeExample;
import com.carrey.demoshardingsphere.service.ILogEsbRequestResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author carrey
 * @since 2021-03-05
 */
@Service
public class LogEsbRequestResumeServiceImpl implements ILogEsbRequestResumeService {

    @Autowired
    private LogEsbRequestResumeMapper logEsbRequestResumeMapper;

    @Override
    public List<LogEsbRequestResume> queryList(String userId) {
        LogEsbRequestResumeExample example = new LogEsbRequestResumeExample();
        LogEsbRequestResumeExample.Criteria criteria = example.createCriteria();
        if (!"0".equals(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        return logEsbRequestResumeMapper.selectByExample(example);
    }
}
