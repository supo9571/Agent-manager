package com.manager.system.service.impl;

import java.util.List;

import com.manager.common.utils.SecurityUtils;
import com.manager.system.domain.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manager.system.mapper.SysOperLogMapper;
import com.manager.system.service.ISysOperLogService;

/**
 * 操作日志 服务层处理
 *
 * @author marvin
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        operLog.setCreateBy(SecurityUtils.getUsername());
        return operLogMapper.selectOperLogList(operLog);
    }

}
