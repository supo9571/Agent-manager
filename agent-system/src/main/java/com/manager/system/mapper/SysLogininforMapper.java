package com.manager.system.mapper;

import java.util.List;

import com.manager.system.domain.SysLogininfor;

/**
 * 系统访问日志情况信息 数据层
 *
 * @author marvin
 */
public interface SysLogininforMapper {
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

}
