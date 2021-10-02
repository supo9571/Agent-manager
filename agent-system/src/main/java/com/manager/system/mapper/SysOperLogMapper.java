package com.manager.system.mapper;

import java.util.List;

import com.manager.system.domain.SysOperLog;

/**
 * 操作日志 数据层
 *
 * @author marvin
 */
public interface SysOperLogMapper {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

}
